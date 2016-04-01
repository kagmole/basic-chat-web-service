package com.kagmole.workshops.basicchat.webservice.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidationFailedException extends ApplicationException {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
	public ValidationFailedException(BindingResult result) {
		super(generateErrorMessage(result),
				ApplicationExceptionType.VALIDATION_FAILED);
	}
	
	private static String generateErrorMessage(BindingResult result) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Validation failed.");
		
		result.getAllErrors().forEach((error) -> {
			builder.append("\n");
			
			if (error instanceof FieldError) {
				builder.append("Field \"");
				builder.append(((FieldError) error).getField());
				
			} else {
				builder.append("Object \"");
				builder.append(error.getObjectName());
			}
			
			builder.append("\": ");
			builder.append(error.getDefaultMessage());
		});
		
		return builder.toString();
	}
}
