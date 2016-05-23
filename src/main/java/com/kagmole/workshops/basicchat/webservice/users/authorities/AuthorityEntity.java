package com.kagmole.workshops.basicchat.webservice.users.authorities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import com.kagmole.workshops.basicchat.webservice.users.UserEntity;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "`authorities`")
@Access(AccessType.PROPERTY)
public class AuthorityEntity implements GrantedAuthority, Serializable {
	
	private static final long serialVersionUID = 1_000000_000000L;
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                CONSTRUCTORS                                *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	public AuthorityEntity() {
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                                 PROPERTIES                                 *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	// Surrogate key
	private Integer authorityId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "`authority_id`")
	public Integer getAuthorityId() {
		return authorityId;
	}
	
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	
	// Authority
	private String authority;
	
	@Override
	@Column(name = "`authority`")
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
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
	
	// Users
	private List<UserEntity> users;
	
	@ManyToMany(mappedBy = "authorities")
	public List<UserEntity> getUsers() {
		return users;
	}
	
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
/*----------------------------------------------------------------------------*\
 *                                                                            *
 *                              GENERIC METHODS                               *
 *                                                                            *
\*----------------------------------------------------------------------------*/
	
	@Override
	public int hashCode() {
		
		return Objects.hash(getAuthority());
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof AuthorityEntity)) {
			return false;
		}
		
		AuthorityEntity other = (AuthorityEntity) obj;
		
		return Objects.equals(getAuthority(), other.getAuthority());
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("AuthorityEntity [authorityId=");
		builder.append(getAuthorityId());
		builder.append(", authority=");
		builder.append(getAuthority());
		builder.append(", creationDate=");
		builder.append(getCreationDate());
		builder.append(", lastUpdateDate=");
		builder.append(getLastUpdateDate());
		builder.append("]");
		
		return builder.toString();
	}
}
