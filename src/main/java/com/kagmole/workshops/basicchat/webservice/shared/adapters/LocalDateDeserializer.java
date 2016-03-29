package com.kagmole.workshops.basicchat.webservice.shared.adapters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
	
	private DateTimeFormatter formatter;
	
	public LocalDateDeserializer() {
		formatter = DateTimeFormatter.ISO_DATE;
	}

	@Override
	public LocalDate deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		
		LocalDate value = null;
		
		try {
			value = LocalDate.parse(parser.getValueAsString(), formatter);
		} catch (DateTimeParseException e) {}
		
		return value;
	}
	
	@Override
	public Class<LocalDate> handledType() {
		return LocalDate.class;
	}
}
