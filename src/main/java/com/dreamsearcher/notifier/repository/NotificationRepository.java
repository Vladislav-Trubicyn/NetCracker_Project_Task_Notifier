package com.dreamsearcher.notifier.repository;

import com.dreamsearcher.notifier.model.Notification;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("!test")
public interface NotificationRepository extends CrudRepository<Notification, String>
{
    Notification findFirstByItemIdAndUserIdAndUserWishIdOrderByRetryCountDesc(String itemId, String userId, String userWishId);
    List<Notification> findAllByCreationTimeBetween(String startDate, String endDate);
    List<Notification> findAllByDeliveryStatusAndCreationTimeBetween(boolean isDelivery, String startDate, String endDate);
    List<Notification> findAllByDeliveryStatus(boolean isDelivery);
}
