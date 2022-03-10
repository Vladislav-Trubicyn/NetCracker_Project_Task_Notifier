package com.dreamsearcher.notifier.restClient.implement;

import com.dreamsearcher.notifier.model.ItemToFind;
import com.dreamsearcher.notifier.model.PriceSetting;
import com.dreamsearcher.notifier.restClient.UserDataDictionaryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties(prefix = "service.userdatadictionary")
@Profile("!test")
public class UserDataDictionaryClientImpl implements UserDataDictionaryClient
{
    private static final String SERVICE_HOST = "http://localhost";
    private static final String SERVICE_PORT = "8080";
    private static final String ITEMS_TO_FIND = "/api/v1/ItemsToFind";
    private static final String PRICE_SETTINGS = "/api/v1/PriceSetting";

    private RestTemplate restTemplate;

    public UserDataDictionaryClientImpl(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public ItemToFind getItemToFindById(String itemToFindId)
    {
        return restTemplate.getForObject(SERVICE_HOST + ":" + SERVICE_PORT + ITEMS_TO_FIND + "/" + itemToFindId, ItemToFind.class);
    }

    @Override
    public List<PriceSetting> getAllPriceSettings()
    {
        return restTemplate.getForObject(SERVICE_HOST + ":" + SERVICE_PORT + PRICE_SETTINGS, java.util.ArrayList.class);
    }

    @Override
    public List<ItemToFind> getItemsToFind()
    {

        return null;
    }
}
