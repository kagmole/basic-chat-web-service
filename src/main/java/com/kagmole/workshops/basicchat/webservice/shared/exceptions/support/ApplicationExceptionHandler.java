package com.kagmole.workshops.basicchat.webservice.shared.exceptions.support;

import com.kagmole.workshops.basicchat.webservice.shared.exceptions.ApplicationException;

import java.time.Instant;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(ApplicationException.class)
	public ExceptionEntity applicationExceptionHandler(
			HttpServletResponse response,
			ApplicationException e) {
		
		// Catch exception and generated an entity based on it
		ExceptionEntity exceptionEntity = new ExceptionEntity();
		
		ResponseStatus status = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
		
		if (status != null) {
			exceptionEntity.setStatusCode(status.code().value());
			exceptionEntity.setStatusMessage(status.code().getReasonPhrase());
		} else {
			exceptionEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			exceptionEntity.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
		
		exceptionEntity.setErrorCode(e.getType().ordinal());
		exceptionEntity.setErrorMessage(e.getType().toString());
		exceptionEntity.setErrorDescription(e.getMessage());
		exceptionEntity.setCreationDate(Instant.now());
		
		// Change response status, which is "OK" (200) by default
		response.setStatus(exceptionEntity.getStatusCode());
		
		return exceptionEntity;
	}
}
