package com.kagmole.workshops.basicchat.webservice.rooms;

import java.io.Serializable;
import java.time.Instant;
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
@Table(name = "`rooms`")
public class RoomEntity implements Serializable {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                CONSTRUCTORS                                *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	public RoomEntity() {
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                 PROPERTIES                                 *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	// Surrogate key
	private Integer roomId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "`room_id`")
	public Integer getRoomId() {
		return roomId;
	}
	
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	
	private String name;
	
	@Column(name = "`name`")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Messages
	private List<MessageEntity> messages;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
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
		
		return Objects.hash(getName());
	}
	
	@Override
	public boolean equals(Object object) {
		
		if (this == object) {
			return true;
		}
		
		if (!(object instanceof RoomEntity)) {
			return false;
		}
		
		RoomEntity other = (RoomEntity) object;
		
		return Objects.equals(getName(), other.getName());
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("RoomEntity [roomId=");
		builder.append(roomId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", messagesCount=");
		builder.append((getMessages() != null ) ? getMessages().size() : 0);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", lastUpdateDate=");
		builder.append(lastUpdateDate);
		builder.append("]");
		
		return builder.toString();
	}
}
