package com.dreamsearcher.notifier.service;

import com.dreamsearcher.notifier.model.Notification;
import com.dreamsearcher.notifier.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService
{
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository)
    {
        this.notificationRepository = notificationRepository;
    }

    public void save(Notification notification)
    {
        notificationRepository.save(notification);
    }

    public Notification getNotificationById(String id)
    {
        return notificationRepository.findById(id).get();
    }

    public void deleteNotificationById(String id)
    {
        notificationRepository.deleteById(id);
    }

}
