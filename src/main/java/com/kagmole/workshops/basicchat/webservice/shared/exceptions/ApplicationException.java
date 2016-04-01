package com.kagmole.workshops.basicchat.webservice.shared.exceptions;

@SuppressWarnings("serial")
public abstract class ApplicationException extends RuntimeException {
	
	protected ApplicationExceptionType type;
	
	protected ApplicationException(String message, ApplicationExceptionType type) {
		super(message);
		
		this.type = type;
	}
	
	public ApplicationExceptionType getType() {
		return type;
	}
	
	public void setType(ApplicationExceptionType type) {
		this.type = type;
	}
}
