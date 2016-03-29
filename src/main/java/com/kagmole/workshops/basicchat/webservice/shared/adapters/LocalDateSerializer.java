package com.kagmole.workshops.basicchat.webservice.shared.adapters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {
	
	private DateTimeFormatter formatter;
	
	public LocalDateSerializer() {
		formatter = DateTimeFormatter.ISO_DATE;
	}
	
	@Override
	public void serialize(LocalDate value, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		generator.writeString(value.format(formatter));
	}
	
	@Override
	public Class<LocalDate> handledType() {
		return LocalDate.class;
	}
}
