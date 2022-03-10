package com.dreamsearcher.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    private String userId;
    private String username;
    private String email;
    private List<UserWishes> userWishesList;

    public static Builder builder()
    {
        return new User().new Builder();
    }

    public class Builder
    {
        public Builder userId(String userId)
        {
            User.this.userId = userId;
            return this;
        }
        public Builder username(String username)
        {
            User.this.username = username;
            return this;
        }
        public Builder email(String email)
        {
            User.this.email = email;
            return this;
        }
        public Builder userWishesList(List<UserWishes> userWishesList)
        {
            User.this.userWishesList = userWishesList;
            return this;
        }
        public User build()
        {
            return User.this;
        }
    }
}
