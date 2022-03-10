package com.dreamsearcher.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item
{
    private String itemId;
    private String runId;
    private String itemName;
    private double price;
    private String link;

    public static Builder builder()
    {
        return new Item().new Builder();
    }

    public class Builder
    {
        public Builder itemId(String itemId)
        {
            Item.this.itemId = itemId;
            return this;
        }
        public Builder runId(String runId)
        {
            Item.this.runId = runId;
            return this;
        }
        public Builder itemName(String itemName)
        {
            Item.this.itemName = itemName;
            return this;
        }
        public Builder price(double price)
        {
            Item.this.price = price;
            return this;
        }
        public Builder link(String link)
        {
            Item.this.link = link;
            return this;
        }
        public Item build()
        {
            return Item.this;
        }
    }
}
