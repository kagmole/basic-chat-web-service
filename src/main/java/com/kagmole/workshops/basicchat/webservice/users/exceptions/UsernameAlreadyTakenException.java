package com.kagmole.workshops.basicchat.webservice.users.exceptions;

import com.kagmole.workshops.basicchat.webservice.shared.exceptions.ApplicationException;
import com.kagmole.workshops.basicchat.webservice.shared.exceptions.ApplicationExceptionType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameAlreadyTakenException extends ApplicationException {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
	public UsernameAlreadyTakenException(String username) {
		super(generateErrorMessage(username),
				ApplicationExceptionType.USERNAME_ALREADY_TAKEN);
	}
	
	private static String generateErrorMessage(String username) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("The username \"");
		builder.append(username);
		builder.append("\" is already taken.");
		
		return builder.toString();
	}
}
