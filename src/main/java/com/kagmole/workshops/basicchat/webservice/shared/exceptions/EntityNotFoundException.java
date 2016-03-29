package com.kagmole.workshops.basicchat.webservice.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends ApplicationException {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
	public EntityNotFoundException(Class<?> entityClass, Object... entityKeys) {
		super(generateErrorMessage(entityClass, entityKeys),
				ApplicationExceptionType.ENTITY_NOT_FOUND);
	}
	
	private static String generateErrorMessage(Class<?> entityClass, Object... entityKeys) {
		StringBuilder builder = new StringBuilder();
		
		builder.append(entityClass.getSimpleName());
		
		if (entityKeys.length > 0) {
			
			builder.append(" with keys {");
			
			for (int i = 0;;) {
				builder.append(entityKeys[i]);
				
				if (++i < entityKeys.length) {
					builder.append(", ");
				} else {
					break;
				}
			}
			
			builder.append("}");
		}
		
		builder.append(" not found.");
		
		return builder.toString();
	}
}
