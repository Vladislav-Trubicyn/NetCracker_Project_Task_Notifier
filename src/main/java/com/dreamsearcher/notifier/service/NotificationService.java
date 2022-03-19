package com.dreamsearcher.notifier.service;

import com.dreamsearcher.notifier.model.Notification;
import com.dreamsearcher.notifier.repository.NotificationRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService
{
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository)
    {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications(String[] date, boolean isDelivery)
    {
        if(!Strings.isEmpty(date[0]) && !Strings.isEmpty(date[1]))
        {
            if(isDelivery)
            {
                return notificationRepository.findAllByDeliveryStatusAndCreationTimeBetween(isDelivery, date[0], date[1]);
            }
            else
            {
                return notificationRepository.findAllByCreationTimeBetween(date[0], date[1]);
            }
        }
        else
        {
            return notificationRepository.findAllByDeliveryStatus(isDelivery);
        }
    }

    public Notification getNotificationById(String id)
    {
        return notificationRepository.findById(id).get();
    }

    public void deleteNotificationById(String id)
    {
        notificationRepository.deleteById(id);
    }

    public void save(Notification notification)
    {
        notificationRepository.save(notification);
    }

    public void saveNotificationsToDataBase(List<Notification> notifications)
    {
        notifications.stream().forEach(notification -> {

            if(notification.isSend())
            {
                notificationRepository.save(notification);
            }

        });
    }

}
