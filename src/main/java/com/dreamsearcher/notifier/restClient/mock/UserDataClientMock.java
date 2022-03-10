package com.dreamsearcher.notifier.restClient.mock;

import com.dreamsearcher.notifier.model.User;
import com.dreamsearcher.notifier.model.UserWishes;
import com.dreamsearcher.notifier.restClient.UserDataClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile("test")
public class UserDataClientMock implements UserDataClient
{
    @Override
    public List<User> getAllUsers()
    {
        return Arrays.asList(
                new User().builder()
                        .userId("1")
                        .username("Vladislav")
                        .email("vlad.trubicyn@mail.ru")
                        .userWishesList(Arrays.asList(
                            new UserWishes().builder().userWishId("1").userId("1").item2FindId("1").priceSettingId("1").values("70000").build(),
                            new UserWishes().builder().userWishId("2").userId("1").item2FindId("1").priceSettingId("4").values("67209").build()))
                        .build(),
                new User().builder()
                        .userId("2")
                        .username("Max")
                        .email("vlad.trubicyn@mail.ru")
                        .userWishesList(Arrays.asList(
                                new UserWishes().builder().userWishId("3").userId("2").item2FindId("2").priceSettingId("2").values("30000").build()))
                        .build(),
                new User().builder()
                        .userId("3")
                        .username("Anton")
                        .email("vlad.trubicyn@mail.ru")
                        .userWishesList(Arrays.asList(
                                new UserWishes().builder().userWishId("4").userId("3").item2FindId("3").priceSettingId("3").values("2000").build()))
                        .build(),
                new User().builder()
                        .userId("4")
                        .username("Gleb")
                        .email("vlad.trubicyn@mail.ru")
                        .userWishesList(Arrays.asList(
                                new UserWishes().builder().userWishId("5").userId("4").item2FindId("1").priceSettingId("4").values("65210").build()))
                        .build()
        );
    }

    public User getUserById(String userId)
    {
        List<User> users = Arrays.asList(
                new User().builder()
                        .userId("1")
                        .username("Vladislav")
                        .email("vlad.trubicyn@mail.ru")
                        .userWishesList(Arrays.asList(
                                new UserWishes().builder().userWishId("1").userId("1").item2FindId("1").priceSettingId("1").values("70000").build(),
                                new UserWishes().builder().userWishId("2").userId("1").item2FindId("1").priceSettingId("4").values("67209").build()))
                        .build(),
                new User().builder()
                        .userId("2")
                        .username("Max")
                        .email("vlad.trubicyn@mail.ru")
                        .userWishesList(Arrays.asList(
                                new UserWishes().builder().userWishId("3").userId("2").item2FindId("2").priceSettingId("2").values("30000").build()))
                        .build(),
                new User().builder()
                        .userId("3")
                        .username("Anton")
                        .email("vlad.trubicyn@mail.ru")
                        .userWishesList(Arrays.asList(
                                new UserWishes().builder().userWishId("4").userId("3").item2FindId("3").priceSettingId("3").values("2000").build()))
                        .build(),
                new User().builder()
                        .userId("4")
                        .username("Gleb")
                        .email("vlad.trubicyn@mail.ru")
                        .userWishesList(Arrays.asList(
                                new UserWishes().builder().userWishId("5").userId("4").item2FindId("1").priceSettingId("4").values("65210").build()))
                        .build()
        );
        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElseThrow();
    }
}
