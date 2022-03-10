package com.dreamsearcher.notifier.restClient.implement;

import com.dreamsearcher.notifier.model.User;
import com.dreamsearcher.notifier.restClient.UserDataClient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@ConfigurationProperties(prefix = "service.userdata")
@Profile("!test")
public class UserDataClientImpl implements UserDataClient
{
    private static final String SERVICE_HOST = "http://localhost";
    private static final String SERVICE_PORT = "8080";
    private static final String USERS_PATH = "/api/v1/users";

    private RestTemplate restTemplate;

    public UserDataClientImpl(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAllUsers()
    {
        return restTemplate.getForObject(SERVICE_HOST + ":" + SERVICE_PORT + USERS_PATH, java.util.ArrayList.class);
    }

    @Override
    public User getUserById(String userId)
    {
        return restTemplate.getForObject(SERVICE_HOST + ":" + SERVICE_PORT + USERS_PATH + "/" + userId, User.class);
    }
}
