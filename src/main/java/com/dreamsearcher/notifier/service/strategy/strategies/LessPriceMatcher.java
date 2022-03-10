package com.dreamsearcher.notifier.service.strategy.strategies;

import com.dreamsearcher.notifier.service.strategy.PriceMatcher;
import com.dreamsearcher.notifier.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessPriceMatcher implements PriceMatcher
{
    @Override
    public String getPricePattern()
    {
        return "<?";
    }

    @Override
    public List<Item> filterItemsByPrice(List<Item> items, String values)
    {
        Double price = Double.parseDouble(values);
        return items.stream().filter(i -> Double.compare(i.getPrice(), price) < 0).collect(Collectors.toList());
    }
}
