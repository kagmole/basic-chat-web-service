package com.kagmole.workshops.basicchat.webservice.shared.adapters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;

public class InstantSerializer extends JsonSerializer<Instant> {

	@Override
	public void serialize(Instant value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		jgen.writeNumber(value.toEpochMilli());
	}
	
	@Override
	public Class<Instant> handledType() {
		return Instant.class;
	}
}
