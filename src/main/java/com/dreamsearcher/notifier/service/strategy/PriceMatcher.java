package com.dreamsearcher.notifier.service.strategy;

import com.dreamsearcher.notifier.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriceMatcher
{
    String getPricePattern();
    List<Item> filterItemsByPrice(List<Item> items, String values);
}
