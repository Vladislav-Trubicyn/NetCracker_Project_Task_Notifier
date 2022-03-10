package com.dreamsearcher.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemToFind
{
    private String itemToFindId;
    private String name;

    public static Builder builder()
    {
        return new ItemToFind().new Builder();
    }

    public class Builder
    {
        public Builder itemToFindId(String itemToFindId)
        {
            ItemToFind.this.itemToFindId = itemToFindId;
            return this;
        }
        public Builder name(String name)
        {
            ItemToFind.this.name = name;
            return this;
        }
        public ItemToFind build()
        {
            return ItemToFind.this;
        }
    }
}
