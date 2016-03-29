package com.kagmole.workshops.basicchat.webservice.shared.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		// When those 2 are false, the content type is defined with the "Accept" header
		configurer.favorParameter(false);
		configurer.favorPathExtension(false);
	}
}