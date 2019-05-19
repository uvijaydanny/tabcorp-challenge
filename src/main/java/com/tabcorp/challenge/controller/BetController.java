package com.tabcorp.challenge.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tabcorp.challenge.model.BetData;
import com.tabcorp.challenge.model.errhandlers.BindingErrorsResponse;
import com.tabcorp.challenge.service.BetDataService;

@RestController
public class BetController {

	@Autowired
	private BetDataService service;
	
	@GetMapping(value="/bets")
	public ResponseEntity<List<BetData>> getAllBets(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		List<BetData> betdataList = new ArrayList<>();
		BetData b =  new BetData();
		b.setBetType("WIN");
		b.setCustomerId(123);
		b.setDateTime(new Date());
		b.setInvestmentAmount(200.00);
		b.setPropNumber(101);
		betdataList.add(b);
		
		betdataList.addAll(service.getAllBetData());
		
		return new ResponseEntity<List<BetData>>(betdataList , headers, HttpStatus.OK);
	}
	
	@PostMapping(value="/bets",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> postBetData(@Valid @RequestBody BetData betdata, BindingResult br) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		if (br.hasErrors()) {
			errors.addAllErrors(br);
			return ResponseEntity.badRequest().body(errors);
		}
		System.out.println(betdata.toString());
		BetData saveBetData = service.saveBetData(betdata);
		return ResponseEntity.ok().body(saveBetData);
	}
	
	@GetMapping("/investment/type")
	public @ResponseBody String getInvestmentPerBetType(@RequestParam String bettype){
	 	String report = service.getInvestmentsPerByteType(bettype);	
	 	return report;
	}
	
	@GetMapping("/investment/customer")
	public @ResponseBody String getInvestmentPerBetType(@RequestParam Integer custId){
	 	String report = service.getInvestmentsPerCustomerId(custId);	
	 	return report;
	}
	
	@GetMapping("/bets/type")
	public @ResponseBody String getBetsPerBetType(@RequestParam String bettype){
	 	String report = service.getBetsperBettype(bettype);	
	 	return report;
	}
	
	@GetMapping("/bets/sold")
	public @ResponseBody String getTotalBetsPerTime(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd/HH:mm") Date from, 
												  @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd/HH:mm") Date to){
	 	String report = service.getTotalBetssoldperTime(from, to);	
	 	return report;
	}
	
	@GetMapping("/health")
	@ResponseStatus(HttpStatus.OK)
	public void getHealth() {
		
	}
}

