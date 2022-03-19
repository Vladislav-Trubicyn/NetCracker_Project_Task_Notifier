package com.dreamsearcher.notifier.controller;

import com.dreamsearcher.notifier.model.Notification;
import com.dreamsearcher.notifier.service.NotificationEngine;
import com.dreamsearcher.notifier.service.NotificationService;
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
    private NotificationService notificationService;

    public NotifiсationController(NotificationEngine notificationEngine, NotificationService notificationService)
    {
        this.notificationEngine = notificationEngine;
        this.notificationService = notificationService;
    }

    /*@Scheduled(fixedRate = 90000)
    public void startProcessNotifications()
    {
        notificationEngine.notificationBuildingProcess(notificationEngine.processCrawledItems());
    }*/

    @GetMapping
    public void startProcessNotifications()
    {
        notificationEngine.notificationBuildingProcess(notificationEngine.processCrawledItems());
    }

    @PostMapping
    public void saveNotificationsToDataBase(@RequestBody List<Notification> notifications)
    {
        notificationService.saveNotificationsToDataBase(notifications);
    }

    /*@GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications(@RequestParam(value = "eventDateTime", required = false) String date[],
                                                                  @RequestParam(value = "deliveryStatus", required = false) boolean isDelivery)
    {
        return ResponseEntity.ok(notificationService.getAllNotifications(date, isDelivery));
    }*/

}
