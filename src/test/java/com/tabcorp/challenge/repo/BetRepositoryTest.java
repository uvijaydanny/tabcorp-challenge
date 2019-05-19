package com.tabcorp.challenge.repo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat; 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tabcorp.challenge.model.BetData;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BetRepositoryTest {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private BetRepository repo;

	@Before
	public void insertData() {
		BetData bd = new BetData();
		bd.setBetType("WIN");
		bd.setCustomerId(123);
		bd.setDateTime(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		bd.setInvestmentAmount(300.00);
		bd.setPropNumber(7001);
		em.persist(bd);
	}
	
	@Test
	public void testInsertFunctionality() {
		Optional<BetData> findById = repo.findById(1L);
		assertThat(findById.get().getBetType()).isEqualTo("WIN");
	}
}
