package com.dreamsearcher.notifier.restClient.implement;

import com.dreamsearcher.notifier.model.Item;
import com.dreamsearcher.notifier.model.Run;
import com.dreamsearcher.notifier.restClient.CrawlerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@ConfigurationProperties(prefix = "service.crawler")
@Profile("!test")
public class CrawlerClientImpl implements CrawlerClient
{
    @Value("${crawler.service.host}")
    private  String SERVICE_HOST;
    @Value("${crawler.service.port}")
    private  String SERVICE_PORT;
    @Value("${crawler.service.item-path}")
    private  String ITEMS_PATH;
    @Value("${crawler.service.run-path}")
    private  String RUNS_PATH;

    private RestTemplate restTemplate;

    public CrawlerClientImpl(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Run> getAllRuns()
    {
        return restTemplate.getForObject( SERVICE_HOST + ":" + SERVICE_PORT + RUNS_PATH, java.util.ArrayList.class);
    }

    @Override
    public List<Item> getAllItems()
    {
        return restTemplate.getForObject( SERVICE_HOST + ":" + SERVICE_PORT + ITEMS_PATH, java.util.ArrayList.class);
    }

    @Override
    public List<Item> getRunItems(String runId)
    {
        return null;
    }
}
