package com.kagmole.workshops.basicchat.webservice.users;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kagmole.workshops.basicchat.webservice.users.messages.MessageEntity;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "`users`")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                CONSTRUCTORS                                *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	public UserEntity() {
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                 PROPERTIES                                 *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	// Surrogate key
	private Integer userId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "`user_id`")
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	// Username
	private String username;
	
	@Column(name = "`username`")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	// First name
	private String firstName;
	
	@Column(name = "`first_name`")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// Last name
	private String lastName;
	
	@Column(name = "`last_name`")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// Birthday
	private LocalDateTime birthday;
	
	@Column(name = "`birthday`")
	public LocalDateTime getBirthday() {
		return birthday;
	}
	
	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}
	
	// Messages
	private List<MessageEntity> messages;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
	public List<MessageEntity> getMessages() {
		return messages;
	}
	
	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}
	
	// Creation date
	private Instant creationDate;
	
	@CreatedDate
	@Column(name = "`creation_date`")
	public Instant getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}
	
	// Last update date
	private Instant lastUpdateDate;
	
	@LastModifiedDate
	@Column(name = "`last_update_date`")
	public Instant getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	public void setLastUpdateDate(Instant lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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
		
		builder.append("UserEntity [userId=");
		builder.append(getUserId());
		builder.append(", username=");
		builder.append(getUsername());
		builder.append(", firstName=");
		builder.append(getFirstName());
		builder.append(", lastName=");
		builder.append(getLastName());
		builder.append(", birthday=");
		builder.append(getBirthday());
		builder.append(", messagesCount=");
		builder.append((getMessages() != null ) ? getMessages().size() : 0);
		builder.append(", creationDate=");
		builder.append(getCreationDate());
		builder.append(", lastUpdateDate=");
		builder.append(getLastUpdateDate());
		builder.append("]");
		
		return builder.toString();
	}
}
