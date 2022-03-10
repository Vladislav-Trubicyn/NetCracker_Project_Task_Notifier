package com.dreamsearcher.notifier.restClient;

import com.dreamsearcher.notifier.model.User;
import java.util.List;

public interface UserDataClient
{
    List<User> getAllUsers();
    User getUserById(String userId);
}
