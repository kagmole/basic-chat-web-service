package com.kagmole.workshops.basicchat.webservice.shared.exceptions.support;

import com.kagmole.workshops.basicchat.webservice.shared.exceptions.ApplicationExceptionType;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class UnhandledExceptionController implements ErrorController {
	
	private static final String ERROR_PATH = "/error";
	
	@Autowired
	private ErrorAttributes errorAttributes;
	
	@RequestMapping(
			value = ERROR_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ExceptionEntity error(
			HttpServletRequest request,
			HttpServletResponse response) {
		
		// Generate an exception entity based on error attributes
		ExceptionEntity exceptionEntity = new ExceptionEntity();
		
		Map<String, Object> errorAttributes = getErrorAttributes(request);
		
		int status = response.getStatus();
		
		exceptionEntity.setStatusCode(status);
		exceptionEntity.setStatusMessage(HttpStatus.valueOf(status).getReasonPhrase());
		exceptionEntity.setErrorCode(ApplicationExceptionType.ERROR.ordinal());
		exceptionEntity.setErrorMessage(ApplicationExceptionType.ERROR.toString());
		exceptionEntity.setErrorDescription((String) errorAttributes.get("message"));
		exceptionEntity.setCreationDate(((Date) errorAttributes.get("timestamp")).toInstant());
		
		return exceptionEntity;
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	private Map<String, Object> getErrorAttributes(
			HttpServletRequest request) {
		
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
		
		return errorAttributes.getErrorAttributes(requestAttributes, false);
	}
}
