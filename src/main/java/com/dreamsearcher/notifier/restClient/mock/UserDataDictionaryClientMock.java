package com.dreamsearcher.notifier.restClient.mock;

import com.dreamsearcher.notifier.model.ItemToFind;
import com.dreamsearcher.notifier.model.PriceSetting;
import com.dreamsearcher.notifier.restClient.UserDataDictionaryClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile("test")
public class UserDataDictionaryClientMock implements UserDataDictionaryClient
{
    @Override
    public ItemToFind getItemToFindById(String itemToFindId)
    {
        List<ItemToFind> itemToFindList = Arrays.asList(
                new ItemToFind().builder().itemToFindId("1").name("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").build(),
                new ItemToFind().builder().itemToFindId("2").name("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").build(),
                new ItemToFind().builder().itemToFindId("3").name("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").build()
        );

        return itemToFindList.stream().filter(itemToFind -> itemToFind.getItemToFindId().equals(itemToFindId)).findFirst().orElseThrow();
    }

    @Override
    public List<PriceSetting> getAllPriceSettings()
    {
        return Arrays.asList(
                new PriceSetting().builder().priceSettingId("1").stringPattern("<?").build(),
                new PriceSetting().builder().priceSettingId("2").stringPattern(">?").build(),
                new PriceSetting().builder().priceSettingId("3").stringPattern("<?").build(),
                new PriceSetting().builder().priceSettingId("4").stringPattern(">?").build()
        );
    }

    @Override
    public List<ItemToFind> getItemsToFind()
    {
        return Arrays.asList(
                new ItemToFind().builder().itemToFindId("1").name("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").build(),
                new ItemToFind().builder().itemToFindId("2").name("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").build(),
                new ItemToFind().builder().itemToFindId("3").name("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").build()
        );
    }
}
