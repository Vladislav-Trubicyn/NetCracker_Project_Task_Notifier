package com.dreamsearcher.notifier.controller;

import com.dreamsearcher.notifier.model.Notification;
import com.dreamsearcher.notifier.model.User;
import com.dreamsearcher.notifier.service.NotificationEngine;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableScheduling
@RestController
@RequestMapping("/api/v1/notifications")
public class NotifiсationController
{
    private NotificationEngine notificationEngine;

    public NotifiсationController(NotificationEngine notificationEngine)
    {
        this.notificationEngine = notificationEngine;
    }

    /*@Scheduled(fixedRate = 90000)
    public void startProcessNotifications()
    {
        notificationEngine.processNotifications(notificationEngine.processCrawledItems());
    }*/

    @GetMapping
    public List<Notification> getAllNotifications()
    {
        return notificationEngine.processCrawledItems();
    }

    //TEMP, Special for task unit test
    @GetMapping("/user/{id}")
    public String getUsername(@PathVariable("id") String id)
    {
        return notificationEngine.getUserByIdForTest(id).getUsername();
    }

}
