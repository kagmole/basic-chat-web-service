package com.kagmole.workshops.basicchat.webservice.shared.exceptions.support;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class ExceptionEntity implements Serializable {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                CONSTRUCTORS                                *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	public ExceptionEntity() {
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                 PROPERTIES                                 *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	// HTTP status code
	private int statusCode;
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	// HTTP status message
	private String statusMessage;
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	// Application error code
	private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	// Application error message
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	// Application error description
	private String errorDescription;
	
	public String getErrorDescription() {
		return errorDescription;
	}
	
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	// Creation date
	private Instant creationDate;
	
	public Instant getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                         OBJECT METHODS OVERRIDING                          *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	@Override
	public int hashCode() {
		
		return Objects.hash(statusCode, errorCode);
	}
	
	@Override
	public boolean equals(Object object) {
		
		if (this == object) {
			return true;
		}
		
		if (!(object instanceof ExceptionEntity)) {
			return false;
		}
		
		ExceptionEntity other = (ExceptionEntity) object;
		
		return Objects.equals(statusCode, other.statusCode)
			&& Objects.equals(errorCode, other.errorCode);
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("ExceptionEntity [statusCode=");
		builder.append(statusCode);
		builder.append(", statusMessage=");
		builder.append(statusMessage);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append("]");
		
		return builder.toString();
	}
}
