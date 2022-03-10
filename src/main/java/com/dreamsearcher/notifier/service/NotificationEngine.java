package com.dreamsearcher.notifier.service;

import com.dreamsearcher.notifier.error.EmailSendTroubleException;
import com.dreamsearcher.notifier.model.*;
import com.dreamsearcher.notifier.repository.NotificationRepository;
import com.dreamsearcher.notifier.restClient.CrawlerClient;
import com.dreamsearcher.notifier.restClient.UserDataClient;
import com.dreamsearcher.notifier.restClient.UserDataDictionaryClient;
import com.dreamsearcher.notifier.service.strategy.PriceMatchSelector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationEngine
{
    @Value("${spring.mail.username}")
    private String emailNotification;

    private CrawlerClient crawlerClient;
    private UserDataClient userDataClient;
    private UserDataDictionaryClient userDataDictionaryClient;
    private NotificationRepository notificationRepository;
    private JavaMailSender javaMailSender;
    private SimpleMailMessage simpleMailMessage;

    private PriceMatchSelector priceMatchSelector;

    public NotificationEngine(CrawlerClient crawlerClient, UserDataClient userDataClient, UserDataDictionaryClient userDataDictionaryClient, NotificationRepository notificationRepository, JavaMailSender javaMailSender, SimpleMailMessage simpleMailMessage, PriceMatchSelector priceMatchSelector)
    {
        this.crawlerClient = crawlerClient;
        this.userDataClient = userDataClient;
        this.userDataDictionaryClient = userDataDictionaryClient;
        this.notificationRepository = notificationRepository;
        this.javaMailSender = javaMailSender;
        this.simpleMailMessage = simpleMailMessage;
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

                ItemToFind itemToFind = itemToFinds.stream().filter(i -> i.getItemToFindId().equals(itemToFindId)).findFirst().orElseThrow();
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

    public void processNotifications(List<Notification> notifications)
    {
        StringBuilder allNotification = new StringBuilder();
        boolean isSave = false;
        int maxSuccsessCount = 5;

        for(int i = 0; i < notifications.size(); i++)
        {
            if(notifications.get(i).getUserId().equals(notifications.get(i+1).getUserId()))
            {
                allNotification.append(notifications.get(i).getNotificationText() + System.lineSeparator());
                isSave = (i + 1) >= notifications.size() - 1 ? true : false;
            }
            else
            {
                isSave = true;
            }

            if(isSave)
            {
                allNotification.append(notifications.get(i).getNotificationText() + System.lineSeparator());

                Notification notificationFromDB = notificationRepository.findFirstByItemIdAndUserIdAndUserWishIdOrderByRetryCountDesc(notifications.get(i).getItemId(), notifications.get(i).getUserId(), notifications.get(i).getUserWishId());
                notifications.get(i).setNotificationText(allNotification.toString());

                if(notificationFromDB != null)
                {
                    if(notificationFromDB.getRetryCount() < maxSuccsessCount)
                    {
                        notifications.get(i).setRetryCount(notificationFromDB.getRetryCount() + 1);

                        if(sendNotification(notifications.get(i)))
                        {
                            notificationRepository.save(notifications.get(i));
                        }
                    }
                }
                else
                {
                    if(sendNotification(notifications.get(i)))
                    {
                        notificationRepository.save(notifications.get(i));
                    }
                }

                allNotification.delete(0, allNotification.length());
                isSave = false;
            }
        }

    }

    private boolean sendNotification(Notification notification)
    {

        User user = userDataClient.getUserById(notification.getUserId());

        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom(emailNotification);
        simpleMailMessage.setSubject("Уважаемый пользователь, "  + user.getUsername());
        simpleMailMessage.setText(notification.getNotificationText());

        try
        {
            javaMailSender.send(simpleMailMessage);
            return true;
        }
        catch (MailException mailException)
        {
            throw new EmailSendTroubleException(mailException.getMessage());
        }
    }


    //TEMP, Special for task unit test
    public User getUserByIdForTest(String userId)
    {
        return userDataClient.getUserById(userId);
    }
}
