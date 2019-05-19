package com.tabcorp.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tabcorp.challenge.model.BetData;
import com.tabcorp.challenge.repo.BetRepository;

@RunWith(SpringRunner.class)
public class BetDataServiceTest {

	@TestConfiguration
	public static class TestConfig{
		@Bean
		public BetDataService getBetDataService() {
			return new BetDataService();
		}
	}
	
	@Autowired
	private BetDataService srv;
	@MockBean
	private BetRepository repo;
	
	BetData bd = new BetData();
	@Before
	public void beforeMethod() {
		bd.setBetType("WIN");
		bd.setCustomerId(123);
		bd.setDateTime(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		bd.setInvestmentAmount(300.00);
		bd.setPropNumber(7001);
	}
	
	@Test
	public void testSaveBetData() {
		Mockito.when(repo.save(any(BetData.class))).thenReturn(bd);
		BetData saveBetData = srv.saveBetData(bd);
		assertThat(saveBetData.getBetType()).isEqualTo("WIN");
	}
	
	@Test
	public void test_getAllBetData() {
		Mockito.when(repo.findAll()).thenReturn(Arrays.asList(bd));
		Collection<BetData> allBetData = srv.getAllBetData();
		assertThat(allBetData.size()).isEqualTo(1);
	}
	
	@Test
	public void test_getInvestmentsPerByteType() {
		Mockito.when(repo.findAllByBetType(any(String.class))).thenReturn(Arrays.asList(bd));
		String investmentsPerByteType = srv.getInvestmentsPerByteType("WIN");
		assertThat(investmentsPerByteType).contains("Total Investment = 300.0");
	}
	
	@Test
	public void test_getInvestmentsPerCustomerId() {
		Mockito.when(repo.findAllByCustomerId(any(Integer.class))).thenReturn(Arrays.asList(bd));
		String investmentsPerCustomerId = srv.getInvestmentsPerCustomerId(123);
		assertThat(investmentsPerCustomerId).contains("123");
	}

	@Test
	public void test_getBetsperBettype() {
		Mockito.when(repo.findAllByBetType(any(String.class))).thenReturn(Arrays.asList(bd));
		String investmentsPerCustomerId = srv.getBetsperBettype("WIN");
		assertThat(investmentsPerCustomerId).contains("Total Bets = 1");
	}

	@Test
	public void getTotalBetssoldperTime() {
		Mockito.when(repo.findAllByDateTimeBetween(any(java.util.Date.class), any(java.util.Date.class))).thenReturn(Arrays.asList(bd));
		String investmentsPerCustomerId = srv.getTotalBetssoldperTime(java.util.Date.from(LocalDateTime.now().minusMinutes(30L).atZone(ZoneId.systemDefault()).toInstant()), 
				java.util.Date.from(LocalDateTime.now().plusMinutes(30L).atZone(ZoneId.systemDefault()).toInstant()));
		System.out.println(java.util.Date.from(LocalDateTime.now().minusMinutes(30L).atZone(ZoneId.systemDefault()).toInstant()));
		System.out.println(java.util.Date.from(LocalDateTime.now().plusMinutes(30L).atZone(ZoneId.systemDefault()).toInstant()));
		System.out.println(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		System.out.println(bd.getDateTime());
		assertThat(investmentsPerCustomerId).contains("Total Bets = 1");
	}
	
	
}
