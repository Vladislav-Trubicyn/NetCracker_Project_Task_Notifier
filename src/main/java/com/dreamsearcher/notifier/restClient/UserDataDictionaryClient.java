package com.dreamsearcher.notifier.restClient;

import com.dreamsearcher.notifier.model.ItemToFind;
import com.dreamsearcher.notifier.model.PriceSetting;

import java.util.List;

public interface UserDataDictionaryClient
{
    ItemToFind getItemToFindById(String itemToFindId);
    List<PriceSetting> getAllPriceSettings();
    List<ItemToFind> getItemsToFind();
}
