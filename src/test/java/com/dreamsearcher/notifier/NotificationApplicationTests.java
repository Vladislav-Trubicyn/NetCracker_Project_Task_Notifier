package com.dreamsearcher.notifier;

import com.dreamsearcher.notifier.model.Notification;
import com.dreamsearcher.notifier.service.NotificationEngine;
import com.dreamsearcher.notifier.service.NotificationService;
import org.junit.jupiter.api.Assertions;
import utils.AppUtility;
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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NotificationApplicationTests
{
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private NotificationEngine notificationEngine;

	@Test
	void contextLoads(){

	}

	@Test
	void getNotifications() throws Exception
	{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("http://localhost:8080/api/v1/notifications?eventDateTime=1646740882176&eventDateTime=1646740882179"))
				//.get("http://localhost:8080/api/v1/notifications"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		//String actualResult = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String expectedResult = AppUtility.getContentFromResourceFile("json/rest/RestApiTest_getNotifications_response.json");

		TestValidationUtility.validateResponse(result.getResponse(), expectedResult);
	}

	@Test
	void checkProcessCrawledItems() throws Exception
	{
		Notification actualResult = notificationEngine.processCrawledItems().get(0);

		Assertions.assertAll(
				() -> Assertions.assertEquals(actualResult.getNotificationText(), "Данный товар Смартфон Samsung Galaxy S21 8/128Gb, SM-G991, фиолетовый фантом с ценой 68510.0 и условием: < 70000 доступен в магазине DNS по данной ссылке url2"),
				() -> Assertions.assertEquals(actualResult.getItemId(), "1"),
				() -> Assertions.assertEquals(actualResult.getUserId(), "1"),
				() -> Assertions.assertEquals(actualResult.getUserWishId(), "1"),
				() -> Assertions.assertEquals(actualResult.getShopName(), "DNS"),
				() -> Assertions.assertEquals(actualResult.isDeliveryStatus(), false),
				() -> Assertions.assertEquals(actualResult.getRetryCount(), 1)
		);
	}


}
