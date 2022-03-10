package com.dreamsearcher.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWishes
{
    private String userWishId;
    private String userId;
    private String item2FindId;
    private String priceSettingId;
    private String values;

    public static Builder builder()
    {
        return new UserWishes().new Builder();
    }

    public class Builder
    {
        public Builder userWishId(String userWishId)
        {
            UserWishes.this.userWishId = userWishId;
            return this;
        }
        public Builder userId(String userId)
        {
            UserWishes.this.userId = userId;
            return this;
        }
        public Builder item2FindId(String item2FindId)
        {
            UserWishes.this.item2FindId = item2FindId;
            return this;
        }
        public Builder priceSettingId(String priceSettingId)
        {
            UserWishes.this.priceSettingId = priceSettingId;
            return this;
        }
        public Builder values(String values)
        {
            UserWishes.this.values = values;
            return this;
        }
        public UserWishes build()
        {
            return UserWishes.this;
        }
    }
}
