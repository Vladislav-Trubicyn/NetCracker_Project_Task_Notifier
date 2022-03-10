package com.dreamsearcher.notifier;

import com.dreamsearcher.notifier.model.Notification;
import org.junit.jupiter.api.Assertions;
import utils.AppUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import utils.TestValidationUtility;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NotificationApplicationTests
{
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads()
	{

	}

	@Test
	void getNotificationsTest() throws Exception
	{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notifications")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String actualResult = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String expectedResult = AppUtility.getContentFromResourceFile("json/ObjectRestApiRestTest_getNotificationsTest_response.json");

		TestValidationUtility.validateResponse(result.getResponse(), expectedResult);
	}

	@Test
	void getUserTest() throws Exception
	{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notifications/user/1")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String actualResult = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		Assertions.assertAll(
				() -> Assertions.assertEquals(actualResult, "Vladislav"),
				() -> Assertions.assertEquals(actualResult, "Vladislav1"),
				() -> Assertions.assertEquals(actualResult, "Vladislav"),
				() -> Assertions.assertEquals(actualResult, "Vladislav2"),
				() -> Assertions.assertEquals(actualResult, "Vladislav")
		);

		/*Assertions.assertEquals(actualResult, "Vladislav");
		Assertions.assertEquals(actualResult, "Vladislav1");
		Assertions.assertEquals(actualResult, "Vladislav");*/

	}
}
