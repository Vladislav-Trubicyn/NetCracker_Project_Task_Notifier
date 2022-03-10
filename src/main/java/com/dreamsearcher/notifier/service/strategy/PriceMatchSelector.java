package com.dreamsearcher.notifier.service.strategy;

import com.dreamsearcher.notifier.error.NoAvailableMatcherException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceMatchSelector
{
    List<PriceMatcher> priceMatchers;

    public PriceMatchSelector(List<PriceMatcher> priceMatchers)
    {
        this.priceMatchers = priceMatchers;
    }

    public PriceMatcher getSuitableMatcher(String pattern)
    {
        return priceMatchers.stream()
                .filter(m -> pattern.equals(m.getPricePattern()))
                .findFirst()
                .orElseThrow(() -> new NoAvailableMatcherException());
    }
}
