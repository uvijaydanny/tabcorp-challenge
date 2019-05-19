package com.tabcorp.challenge.model.errhandlers;

import java.time.LocalDateTime;


public class CustomException {

	private String message;
	private LocalDateTime now;
	private String description;
	
	public CustomException(String message, LocalDateTime now, String description) {
		super();
		this.message = message;
		this.now = now;
		this.description = description;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the now
	 */
	public LocalDateTime getNow() {
		return now;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	
	
}
