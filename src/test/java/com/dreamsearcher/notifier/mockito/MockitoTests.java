package com.dreamsearcher.notifier.mockito;

import com.dreamsearcher.notifier.model.User;
import com.dreamsearcher.notifier.restClient.UserDataClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

/*@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")*/
/*public class MockitoTests
{
    @MockBean
    UserDataClient  userDataClient;


    @Test
    void initialize()
    {
        Mockito.when(userDataClient.getUserById(Mockito.anyString())).thenReturn(buildUser());
    }

    @Test
    public void getUserMockito()
    {

    }

    private User buildUser()
    {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setEmail("email@mail.ru");
        user.setUsername("username");
        user.setUserWishesList(null);
        return user;
    }
}*/
