package com.kagmole.workshops.basicchat.webservice.messages;

import com.kagmole.workshops.basicchat.webservice.shared.miscellaneous.ValidationGroup.Create;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class MessageForm implements Serializable {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                CONSTRUCTORS                                *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	public MessageForm() {
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                 PROPERTIES                                 *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	// Content
	@NotNull(
			message = "Content must be set.",
			groups = Create.class)
	@NotBlank(
			message = "Content cannot be blank.",
			groups = Create.class)
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                              GENERIC METHODS                               *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	@Override
	public int hashCode() {
		
		return Objects.hash(getContent());
	}
	
	@Override
	public boolean equals(Object object) {
		
		if (this == object) {
			return true;
		}
		
		if (!(object instanceof MessageForm)) {
			return false;
		}
		
		MessageForm other = (MessageForm) object;
		
		return Objects.equals(getContent(), other.getContent());
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("MessageForm [content=");
		builder.append(content);
		builder.append("]");
		
		return builder.toString();
	}
}
