package com.tabcorp.challenge;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabcorp.challenge.controller.BetController;
import com.tabcorp.challenge.model.BetData;
import com.tabcorp.challenge.service.BetDataService;

import io.jsonwebtoken.lang.Arrays;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChallengeApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BetDataService srv;
	
	@Test
	public void contextLoads() throws Exception {
		BetData b =  new BetData();
		b.setBetType("WIN");
		b.setCustomerId(123);
		b.setDateTime(new Date());
		b.setInvestmentAmount(200.00);
		b.setPropNumber(101);
		Mockito.when(srv.getAllBetData()).thenReturn(java.util.Arrays.asList(b));
		String accessToken = getToken();
		this.mvc.perform(MockMvcRequestBuilders.get("/bets")
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void test_PostBetData() throws Exception {
		BetData b =  new BetData();
		b.setBetType("WIN");
		b.setCustomerId(123);
		b.setDateTime(Date.from(LocalDateTime.now().plusMinutes(30L).atZone(ZoneId.systemDefault()).toInstant()));
		b.setInvestmentAmount(200.00);
		b.setPropNumber(101);
		ObjectMapper om = new ObjectMapper();
		String writeValueAsString = om.writeValueAsString(b);
		Mockito.when(srv.saveBetData(any(BetData.class))).thenReturn(b);
		String accessToken = getToken();
		this.mvc.perform(MockMvcRequestBuilders.post("/bets").content(writeValueAsString).contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("$.PropNumber", is(101))).andDo(MockMvcResultHandlers.print());
	}
	
	public String getToken() throws Exception {
		ResultActions result = this.mvc.perform(MockMvcRequestBuilders.post("/token/generate-token").content("{\"username\":\"danny\",\"password\":\"password\"}")
				.contentType("application/json;charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print());
		String resultString = result.andReturn().getResponse().getContentAsString();
		System.out.println(resultString);
		ObjectMapper om = new ObjectMapper();
		JsonNode readValue = om.readValue(resultString, JsonNode.class);
		JsonNode jsonNode = readValue.get("result");
		String token = jsonNode.get("token").asText();
		return token;
	}

}
