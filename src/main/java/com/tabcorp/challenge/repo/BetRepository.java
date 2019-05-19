package com.tabcorp.challenge.repo;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabcorp.challenge.model.BetData;

public interface BetRepository extends JpaRepository<BetData, Long> {
	
	Collection<BetData> findAllByBetType(String bettype);

	Collection<BetData> findAllByCustomerId(Integer custId);

	Collection<BetData> findAllByDateTimeBetween(Date fromDate, Date toDate);

}
