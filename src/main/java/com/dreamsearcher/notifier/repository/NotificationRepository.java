package com.dreamsearcher.notifier.repository;

import com.dreamsearcher.notifier.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String>
{
    Notification findFirstByItemIdAndUserIdAndUserWishIdOrderByRetryCountDesc(String itemId, String userId, String userWishId);
}
