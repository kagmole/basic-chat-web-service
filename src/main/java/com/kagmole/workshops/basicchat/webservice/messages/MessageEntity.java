package com.kagmole.workshops.basicchat.webservice.messages;

import com.kagmole.workshops.basicchat.webservice.users.UserEntity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "`messages`")
public class MessageEntity implements Serializable {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                CONSTRUCTORS                                *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	public MessageEntity() {
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                 PROPERTIES                                 *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	// Surrogate key
	private Integer messageId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "`message_id`")
	public Integer getMessageId() {
		return messageId;
	}
	
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	
	// Author
	private UserEntity author;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "`author_user_id`", referencedColumnName = "`user_id`")
	public UserEntity getAuthor() {
		return author;
	}
	
	public void setAuthor(UserEntity author) {
		this.author = author;
	}
	
	// Content
	private String content;
	
	@Column(name = "`content`")
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
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
		
		return Objects.hash(getAuthor(), getContent());
	}
	
	@Override
	public boolean equals(Object object) {
		
		if (this == object) {
			return true;
		}
		
		if (!(object instanceof MessageEntity)) {
			return false;
		}
		
		MessageEntity other = (MessageEntity) object;
		
		return Objects.equals(getAuthor(), other.getAuthor())
			&& Objects.equals(getContent(), other.getContent());
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("MessageEntity [messageId=");
		builder.append(messageId);
		builder.append(", author=");
		builder.append(author);
		builder.append(", content=");
		builder.append(content);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", lastUpdateDate=");
		builder.append(lastUpdateDate);
		builder.append("]");
		
		return builder.toString();
	}
}
