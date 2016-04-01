package com.kagmole.workshops.basicchat.webservice.shared.exceptions;

public enum ApplicationExceptionType {
	
	ERROR					("Error"),
	
	ENTITY_NOT_FOUND		("Entity Not Found"),
	VALIDATION_FAILED		("Validation Failed"),
	USERNAME_ALREADY_TAKEN	("Username Already Taken");
	
	private String message;
	
	private ApplicationExceptionType(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
