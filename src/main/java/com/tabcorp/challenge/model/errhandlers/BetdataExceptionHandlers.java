package com.tabcorp.challenge.model.errhandlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class BetdataExceptionHandlers extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomException> handleAllExceptions(Exception ex, WebRequest request) {
	  
	  CustomException ce = new CustomException(ex.getMessage(),LocalDateTime.now(),request.getContextPath());
	  System.out.println("ALL Exception ***********************");
	  return new ResponseEntity<>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
