package com.kagmole.workshops.basicchat.webservice.shared.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = LocalPastValidator.class)
public @interface LocalPast {
	
	String message() default "Must belong to the past.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
