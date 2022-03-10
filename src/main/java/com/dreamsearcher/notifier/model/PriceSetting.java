package com.dreamsearcher.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceSetting
{
    private String priceSettingId;
    private String stringPattern;

    public static Builder builder()
    {
        return new PriceSetting().new Builder();
    }

    public class Builder
    {
        public Builder priceSettingId(String priceSettingId)
        {
            PriceSetting.this.priceSettingId = priceSettingId;
            return this;
        }
        public Builder stringPattern(String stringPattern)
        {
            PriceSetting.this.stringPattern = stringPattern;
            return this;
        }
        public PriceSetting build()
        {
            return PriceSetting.this;
        }
    }
}
