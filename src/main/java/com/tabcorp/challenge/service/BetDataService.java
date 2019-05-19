package com.tabcorp.challenge.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabcorp.challenge.model.BetData;
import com.tabcorp.challenge.repo.BetRepository;

@Service
public class BetDataService {

	Double total = 0.0;
	Long bets = Long.valueOf(0);
	@Autowired
	private BetRepository repo;
	
	public BetData saveBetData(BetData b) {
		return repo.save(b);
	}
	
	public Collection<BetData> getAllBetData(){
		return repo.findAll();
	}

	public String getInvestmentsPerByteType(String bettype) {
		Collection<BetData> betdataList = repo.findAllByBetType(bettype);
		StringBuilder strbuilder = new StringBuilder();
		String join = "</td><td>";
		total = 0.0;
		betdataList.forEach(b -> {
			strbuilder.append("<tr><td>")
					  .append(b.getPropNumber().toString())
					  .append(join)
					  .append(b.getCustomerId().toString())
					  .append(join)
					  .append(b.getInvestmentAmount().toString())
					  .append(join)
					  .append(b.getDateTime().toString())
					  .append(join)
					  .append(b.getBetType()).append("</td></tr>");
			total += b.getInvestmentAmount();
		});
		return String.format("<html><head><style>table, td, th{vertical-align:top;border:solid 1px #666;border-collapse: collapse;}</style>"
	              + "</head><body><h2>Investment per Bet Type</h2><table>" 
				  + "<tr><th>Prop Number</th><th>Customer ID</th><th>Investment Amount</th><th>Date Time</th><th>Bet Type</th>" 
	              + "%s</table><h3>Total Investment = %s</h3></body></html>", strbuilder.toString(),String.valueOf(total));
	}
	
	public String getInvestmentsPerCustomerId(Integer custId) {
		Collection<BetData> betdataList = repo.findAllByCustomerId(custId);
		StringBuilder strbuilder = new StringBuilder();
		String join = "</td><td>";
		total = 0.0;
		betdataList.forEach(b -> {
			strbuilder.append("<tr><td>")
					  .append(b.getPropNumber().toString())
					  .append(join)
					  .append(b.getCustomerId().toString())
					  .append(join)
					  .append(b.getInvestmentAmount().toString())
					  .append(join)
					  .append(b.getDateTime().toString())
					  .append(join)
					  .append(b.getBetType()).append("</td></tr>");
			total += b.getInvestmentAmount();
		});
		return String.format("<html><head><style>table, td, th{vertical-align:top;border:solid 1px #666;border-collapse: collapse;}</style>"
	              + "</head><body><h2>Investment per Customer Id</h2><table>" 
				  + "<tr><th>Prop Number</th><th>Customer ID</th><th>Investment Amount</th><th>Date Time</th><th>Bet Type</th>" 
	              + "%s</table><h3>Total Investment = %s</h3></body></html>", strbuilder.toString(),String.valueOf(total));
	}

	public String getBetsperBettype(String bettype) {
		Collection<BetData> betdataList = repo.findAllByBetType(bettype);
		StringBuilder strbuilder = new StringBuilder();
		String join = "</td><td>";
		bets = Long.valueOf(0);
		betdataList.forEach(b -> {
			strbuilder.append("<tr><td>")
					  .append(b.getPropNumber().toString())
					  .append(join)
					  .append(b.getCustomerId().toString())
					  .append(join)
					  .append(b.getInvestmentAmount().toString())
					  .append(join)
					  .append(b.getDateTime().toString())
					  .append(join)
					  .append(b.getBetType()).append("</td></tr>");
			bets += 1;
		});
		return String.format("<html><head><style>table, td, th{vertical-align:top;border:solid 1px #666;border-collapse: collapse;}</style>"
	              + "</head><body><h2>Bets per Bet Type</h2><table>" 
				  + "<tr><th>Prop Number</th><th>Customer ID</th><th>Investment Amount</th><th>Date Time</th><th>Bet Type</th>" 
	              + "%s</table><h3>Total Bets = %s</h3></body></html>", strbuilder.toString(),String.valueOf(bets));
	}

	public String getTotalBetssoldperTime(Date fromDate, Date toDate) {
		Collection<BetData> betdataList = repo.findAllByDateTimeBetween(fromDate, toDate);
		StringBuilder strbuilder = new StringBuilder();
		String join = "</td><td>";
		bets = Long.valueOf(0);
		betdataList.forEach(b -> {
			strbuilder.append("<tr><td>")
					  .append(b.getPropNumber().toString())
					  .append(join)
					  .append(b.getCustomerId().toString())
					  .append(join)
					  .append(b.getInvestmentAmount().toString())
					  .append(join)
					  .append(b.getDateTime().toString())
					  .append(join)
					  .append(b.getBetType()).append("</td></tr>");
			bets += 1;
		});
		return String.format("<html><head><style>table, td, th{vertical-align:top;border:solid 1px #666;border-collapse: collapse;}</style>"
	              + "</head><body><h2>Bets sold within a specific period</h2><table>" 
				  + "<tr><th>Prop Number</th><th>Customer ID</th><th>Investment Amount</th><th>Date Time</th><th>Bet Type</th>" 
	              + "%s</table><h3>Total Bets = %s</h3></body></html>", strbuilder.toString(),String.valueOf(bets));
	}
}
