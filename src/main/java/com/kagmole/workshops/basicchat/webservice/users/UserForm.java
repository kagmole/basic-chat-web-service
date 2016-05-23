package com.kagmole.workshops.basicchat.webservice.users;

import com.kagmole.workshops.basicchat.webservice.shared.constraints.LocalPast;
import com.kagmole.workshops.basicchat.webservice.shared.miscellaneous.ValidationGroup.Create;
import com.kagmole.workshops.basicchat.webservice.shared.miscellaneous.ValidationGroup.Update;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserForm implements Serializable {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                CONSTRUCTORS                                *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	public UserForm() {
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                 PROPERTIES                                 *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	// Username
	private String username;
	
	@NotNull(
			message = "Username must be set.",
			groups = Create.class)
	@Pattern(
			regexp = "^(?=.{3,255}$)(?![-_.])(?!.*[-_.]{2})[-a-zA-Z0-9_.]+(?<![-_.])$",
			message = "Username must contain at least 3 characters and cannot be longer than 255 characters.\n"
					+ "It can contain letters, digits, hyphens (-), underscores (_) and dots (.).\n"
					+ "However, it can only start and finish with a letter or a digit.\n"
					+ "It also can't contain 2 hyphens, underscores or dots in a row.",
			groups = Create.class)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	// First name
	private String firstName;
	
	@NotNull(
			message = "First name must be set.",
			groups = Create.class)
	@Pattern(
			regexp = "[^\\s]+",
			message = "First name cannot be blank.",
			groups = {
				Create.class,
				Update.class
			})
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// Last name
	private String lastName;
	
	@NotNull(
			message = "Last name must be set.",
			groups = Create.class)
	@Pattern(
			regexp = "[^\\s]+",
			message = "Last name cannot be blank.",
			groups = {
				Create.class,
				Update.class
			})
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// Birthday
	private LocalDate birthday;
	
	@NotNull(
			message = "Birthday must be set.",
			groups = Create.class)
	@LocalPast(
			message = "Birthday must belong to the past.",
			groups = {
				Create.class,
				Update.class
			})
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                              GENERIC METHODS                               *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	@Override
	public int hashCode() {
		
		return Objects.hash(getUsername());
	}
	
	@Override
	public boolean equals(Object object) {
		
		if (this == object) {
			return true;
		}
		
		if (!(object instanceof UserEntity)) {
			return false;
		}
		
		UserEntity other = (UserEntity) object;
		
		return Objects.equals(getUsername(), other.getUsername());
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("UserForm [username=");
		builder.append(getUsername());
		builder.append(", firstName=");
		builder.append(getFirstName());
		builder.append(", lastName=");
		builder.append(getLastName());
		builder.append(", birthday=");
		builder.append(getBirthday());
		builder.append("]");
		
		return builder.toString();
	}
}
