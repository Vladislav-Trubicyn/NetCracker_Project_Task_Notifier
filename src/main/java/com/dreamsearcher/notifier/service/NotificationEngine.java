package com.dreamsearcher.notifier.service;

import com.dreamsearcher.notifier.model.*;
import com.dreamsearcher.notifier.repository.NotificationRepository;
import com.dreamsearcher.notifier.restClient.CrawlerClient;
import com.dreamsearcher.notifier.restClient.UserDataClient;
import com.dreamsearcher.notifier.restClient.UserDataDictionaryClient;
import com.dreamsearcher.notifier.service.strategy.PriceMatchSelector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationEngine
{
    @Value("${sender.servie.host}")
    private String SERVICE_HOST;
    @Value("${sender.servie.port}")
    private String SERVICE_PORT;
    @Value("${sender.servie.path}")
    private String SERVICE_PATH;

    private CrawlerClient crawlerClient;
    private UserDataClient userDataClient;
    private UserDataDictionaryClient userDataDictionaryClient;
    private NotificationRepository notificationRepository;
    private RestTemplate restTemplate;

    private PriceMatchSelector priceMatchSelector;

    public NotificationEngine(CrawlerClient crawlerClient, UserDataClient userDataClient, UserDataDictionaryClient userDataDictionaryClient, NotificationRepository notificationRepository, RestTemplate restTemplate, PriceMatchSelector priceMatchSelector)
    {
        this.crawlerClient = crawlerClient;
        this.userDataClient = userDataClient;
        this.userDataDictionaryClient = userDataDictionaryClient;
        this.notificationRepository = notificationRepository;
        this.restTemplate = restTemplate;
        this.priceMatchSelector = priceMatchSelector;
    }

    public List<Notification> processCrawledItems()
    {
        List<Notification> notifications = new ArrayList<>();

        List<User> users = userDataClient.getAllUsers();
        List<PriceSetting> priceSettings = userDataDictionaryClient.getAllPriceSettings();
        List<ItemToFind> itemToFinds = userDataDictionaryClient.getItemsToFind();

        List<Run> unprocessedRuns = crawlerClient.getAllRuns().stream().filter(run -> run.getIsProcessed() == false).collect(Collectors.toList());
        List<Item> unprocessedItems = unprocessedRuns.stream().flatMap(run -> crawlerClient.getRunItems(run.getRunId()).stream()).collect(Collectors.toList());

        users.forEach(user -> {
            user.getUserWishesList().stream().forEach(userWishes -> {

                String itemToFindId = userWishes.getItem2FindId();
                String priceSettingId = userWishes.getPriceSettingId();
                String values = userWishes.getValues();

                PriceSetting priceSetting = priceSettings.stream().filter(p -> p.getPriceSettingId().equals(priceSettingId)).findFirst().orElseThrow();

                List<Item> suitableItems = unprocessedItems.stream().filter(i -> i.getItemId().equals(itemToFindId)).collect(Collectors.toList());
                List<Item> filteredItems = priceMatchSelector.getSuitableMatcher(priceSetting.getStringPattern()).filterItemsByPrice(suitableItems,values);

                filteredItems.stream().forEach(item -> {

                    String shopName = unprocessedRuns.stream().filter(run -> run.getRunId().equals(item.getRunId())).map(run -> run.getShopName()).findFirst().orElseThrow();
                    notifications.add(new Notification().builder()
                            .creationTime("" + System.currentTimeMillis())
                            .itemId(item.getItemId())
                            .shopName(shopName)
                            .userId(user.getUserId())
                            .userWishId(userWishes.getUserWishId())
                            .deliveryStatus(false)
                            .deliveredTime("")
                            .retryCount(1)
                            .notificationText(String.format("Данный товар %s с ценой %s и условием: %s %s доступен в магазине %s по данной ссылке %s",
                                    item.getItemName(), item.getPrice(), priceSetting.getStringPattern().replace("?",""), values, shopName, item.getLink()))
                            .build()
                    );

                });


            });
        });

        return notifications;
    }

    public void notificationBuildingProcess(List<Notification> billets)
    {
        int maxSuccsessCount = 5;
        List<Notification> notifications = new ArrayList<>();

        billets.stream().forEach(billet -> {

            Notification notificationFromList = notifications.stream().filter(
                    notification -> notification.getUserId().equals(billet.getUserId()) &&
                                    notification.getUserWishId().equals(billet.getUserWishId())).findFirst().orElse(null);

            if(notificationFromList == null)
            {
                Notification buildNotification = buildNotification(billet,maxSuccsessCount);

                if(buildNotification != null)
                {
                    notifications.add(buildNotification(billet,maxSuccsessCount));
                }
            }
            else
            {
                int indexObject = notifications.indexOf(notificationFromList);

                if(indexObject != -1)
                {
                    notificationFromList.setNotificationText(notificationFromList.getNotificationText() + System.lineSeparator() + billet.getNotificationText());
                    notifications.set(indexObject, notificationFromList);
                }
            }

        });

        restTemplate.postForObject(SERVICE_HOST + ":" + SERVICE_PORT + SERVICE_PATH, notifications, List.class);
    }

    private Notification buildNotification(Notification notification, int maxSuccsessCount)
    {
        User user = userDataClient.getUserById(notification.getUserId());

        Notification notificationFromDB = notificationRepository.findFirstByItemIdAndUserIdAndUserWishIdOrderByRetryCountDesc(
                notification.getItemId(),
                notification.getUserId(),
                notification.getUserWishId());

        notification.setUsername(user.getUsername());
        notification.setUserEmail(user.getEmail());

        if(notificationFromDB != null)
        {
            if(notificationFromDB.getRetryCount() < maxSuccsessCount)
            {
                notification.setRetryCount(notificationFromDB.getRetryCount() + 1);
            }
            else
            {
                notification = null;
            }
        }

        return notification;
    }


}
