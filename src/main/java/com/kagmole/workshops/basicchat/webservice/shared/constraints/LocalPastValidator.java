package com.kagmole.workshops.basicchat.webservice.shared.constraints;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocalPastValidator implements ConstraintValidator<LocalPast, LocalDate> {
	
	@Override
	public void initialize(LocalPast constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		
		if (value == null) {
			return true;
		}
		
		return value.isBefore(LocalDate.now());
	}
}
