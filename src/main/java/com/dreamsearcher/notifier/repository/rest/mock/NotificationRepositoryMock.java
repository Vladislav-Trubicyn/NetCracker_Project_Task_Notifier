package com.dreamsearcher.notifier.repository.rest.mock;

import com.dreamsearcher.notifier.controller.NotifiсationController;
import com.dreamsearcher.notifier.model.Notification;
import com.dreamsearcher.notifier.repository.NotificationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Profile("test")
public class NotificationRepositoryMock implements NotificationRepository
{
    List<Notification> notifications = new ArrayList<>();

    public NotificationRepositoryMock()
    {
        notifications = Arrays.asList(

                new Notification("d9f89d0e-bc60-4a62-a883-b6965bb505de",
                        "Tut kucha tovarov", "1", "1", "2",
                        "DNS", false, 1, "1646740882176", ""),

                new Notification("1e27fe29-7093-45bc-866d-ecb925c13d1e",
                        "Tut kucha tovarov", "2", "2", "3",
                        "DNS", false, 1, "1646740882177", ""),

                new Notification("7428b91f-a59d-4c3e-9aef-6ea77bb76864",
                        "Tut kucha tovarov", "3", "3", "4",
                        "DNS", false, 1, "1646740882178", ""),

                new Notification("57c53c88-cbf3-4a05-b841-66d4fad0e000",
                        "Tut kucha tovarov", "1", "4", "5",
                        "DNS", false, 1, "1646740882179", "")
        );
    }


    @Override
    public Notification findFirstByItemIdAndUserIdAndUserWishIdOrderByRetryCountDesc(String itemId, String userId, String userWishId)
    {
        return notifications.stream().filter(n ->
                n.getItemId().equals(itemId) && n.getUserId().equals(userId) && n.getUserWishId().equals(userWishId))
                .findFirst().orElse(null);
    }

    @Override
    public List<Notification> findAllByCreationTimeBetween(String startDate, String endDate)
    {
        return notifications.stream().filter(n ->
                Long.parseLong(n.getCreationTime()) >= Long.parseLong(startDate) &&
                        Long.parseLong(n.getCreationTime()) <= Long.parseLong(endDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findAllByDeliveryStatusAndCreationTimeBetween(boolean isDelivery, String startDate, String endDate)
    {
        return notifications.stream().filter(n ->
                Long.parseLong(n.getCreationTime()) >= Long.parseLong(startDate) &&
                        Long.parseLong(n.getCreationTime()) <= Long.parseLong(endDate) &&
                        n.isDeliveryStatus() == isDelivery)
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findAllByDeliveryStatus(boolean isDelivery)
    {
        return notifications.stream().filter(n -> n.isDeliveryStatus() == isDelivery).collect(Collectors.toList());
    }

    @Override
    public <S extends Notification> S save(S entity)
    {
        notifications.add(entity);
        return null;
    }

    @Override
    public <S extends Notification> Iterable<S> saveAll(Iterable<S> entities)
    {
        return null;
    }

    @Override
    public Optional<Notification> findById(String s)
    {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s)
    {
        return false;
    }

    @Override
    public Iterable<Notification> findAll()
    {
        return null;
    }

    @Override
    public Iterable<Notification> findAllById(Iterable<String> strings)
    {
        return null;
    }

    @Override
    public long count()
    {
        return 0;
    }

    @Override
    public void deleteById(String s)
    {

    }

    @Override
    public void delete(Notification entity)
    {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings)
    {

    }

    @Override
    public void deleteAll(Iterable<? extends Notification> entities)
    {

    }

    @Override
    public void deleteAll()
    {

    }
}
