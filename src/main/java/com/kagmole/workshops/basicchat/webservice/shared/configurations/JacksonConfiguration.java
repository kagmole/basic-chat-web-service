package com.kagmole.workshops.basicchat.webservice.shared.configurations;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.kagmole.workshops.basicchat.webservice.shared.adapters.InstantSerializer;
import com.kagmole.workshops.basicchat.webservice.shared.adapters.LocalDateDeserializer;
import com.kagmole.workshops.basicchat.webservice.shared.adapters.LocalDateSerializer;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		
	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	    
	    builder
	    	.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX)
	    	.indentOutput(true)
	    	.serializers(
	    			new InstantSerializer(),
	    			new LocalDateSerializer())
	    	.deserializerByType(LocalDate.class, new LocalDateDeserializer());
	    
	    return builder;
	}
}
