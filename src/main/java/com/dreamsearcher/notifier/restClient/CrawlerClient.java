package com.dreamsearcher.notifier.restClient;

import com.dreamsearcher.notifier.model.Item;
import com.dreamsearcher.notifier.model.Run;

import java.util.List;

public interface CrawlerClient
{
    List<Run> getAllRuns();
    List<Item> getAllItems();
    List<Item> getRunItems(String runId);
}
