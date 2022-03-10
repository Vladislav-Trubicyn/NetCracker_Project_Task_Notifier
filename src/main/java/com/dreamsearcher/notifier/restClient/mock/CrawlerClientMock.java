package com.dreamsearcher.notifier.restClient.mock;

import com.dreamsearcher.notifier.model.Item;
import com.dreamsearcher.notifier.model.Run;
import com.dreamsearcher.notifier.restClient.CrawlerClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
@Profile("test")
public class CrawlerClientMock implements CrawlerClient
{
    @Override
    public List<Run> getAllRuns()
    {
        return Arrays.asList(
                new Run().builder().runId("1").dateTime(new Timestamp(System.currentTimeMillis())).shopName("DNS").isProcessed(false).build()
                /*new Run().builder().runId("2").dateTime(new Timestamp(System.currentTimeMillis())).shopName("dns2").isProcessed(false).build(),
                new Run().builder().runId("3").dateTime(new Timestamp(System.currentTimeMillis())).shopName("dNs3").isProcessed(false).build()*/
        );
    }

    @Override
    public List<Item> getAllItems()
    {
        return Arrays.asList(
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(70999).link("url1").build(),
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(68510).link("url2").build(),
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(67210).link("url3").build(),
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(67211).link("url4").build(),
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(65210).link("url5").build(),

                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(30999).link("url6").build(),
                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(25467).link("url7").build(),
                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(25421).link("url8").build(),
                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(15040).link("url9").build(),
                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(9642).link("url10").build(),

                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(10001).link("url11").build(),
                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(4567).link("url12").build(),
                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(6534).link("url13").build(),
                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(45867).link("url14").build(),
                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(1587).link("url15").build()
        );
    }

    @Override
    public List<Item> getRunItems(String runId)
    {
        return Arrays.asList(
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(70999).link("url1").build(),
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(68510).link("url2").build(),
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(67210).link("url3").build(),
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(67211).link("url4").build(),
                new Item().builder().itemId("1").runId("1").itemName("Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом").price(65210).link("url5").build(),

                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(30999).link("url6").build(),
                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(25467).link("url7").build(),
                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(25421).link("url8").build(),
                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(15040).link("url9").build(),
                new Item().builder().itemId("2").runId("1").itemName("Смартфон Samsung Galaxy S21 8/256Gb, SM-G991, фиолетовый фантом").price(9642).link("url10").build(),

                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(10001).link("url11").build(),
                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(4567).link("url12").build(),
                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(6534).link("url13").build(),
                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(45867).link("url14").build(),
                new Item().builder().itemId("3").runId("1").itemName("Смартфон Samsung Galaxy S21 8/512Gb, SM-G991, фиолетовый фантом").price(1587).link("url15").build()
        );
    }
}
