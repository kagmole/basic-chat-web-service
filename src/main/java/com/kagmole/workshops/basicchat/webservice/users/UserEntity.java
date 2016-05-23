package com.kagmole.workshops.basicchat.webservice.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kagmole.workshops.basicchat.webservice.messages.MessageEntity;
import com.kagmole.workshops.basicchat.webservice.users.authorities.AuthorityEntity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "`users`")
@Access(AccessType.PROPERTY)
public class UserEntity implements Serializable, UserDetails {
	
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
	
	@Override
	@Column(name = "`username`", unique = true)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	// Authorities
	private Set<AuthorityEntity> authorities;
	
	@Override
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "`users_authorities`",
		joinColumns = @JoinColumn(
			name = "`user_id`",
			referencedColumnName = "`user_id`"
		),
		inverseJoinColumns = @JoinColumn(
			name = "`authority_id`",
			referencedColumnName = "`authority_id`"
		)
	)
	public Set<AuthorityEntity> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Set<AuthorityEntity> authorities) {
		this.authorities = authorities;
	}
	
	// Password
	private String password;
	
	@Override
	@JsonIgnore
	@Column(name = "`password`")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Account non expired
	public boolean accountNonExpired = true;
	
	@Override
	@JsonIgnore
	@Column(name = "`account_non_expired`")
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	// Account non locked
	private boolean accountNonLocked = true;
	
	@Override
	@JsonIgnore
	@Column(name = "`account_non_locked`")
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonExpired = accountNonLocked;
	}
	
	// Credentials non expired
	private boolean credentialsNonExpired = true;
	
	@Override
	@JsonIgnore
	@Column(name = "`credentials_non_expired`")
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	// Enabled
	private boolean enabled = true;
	
	@Override
	@JsonIgnore
	@Column(name = "`enabled`")
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	private LocalDate birthday;
	
	@Column(name = "`birthday`")
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	// Messages
	private List<MessageEntity> messages;
	
	@JsonIgnore
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
		
		Set<AuthorityEntity> authorities = getAuthorities();
		
		StringJoiner joiner = new StringJoiner("\", \"", "[\"", "\"]");
		
		if (authorities != null) {
			
			authorities.forEach(authority -> joiner.add(authority.getAuthority()));
		}
		
		builder.append(", authorities=");
		builder.append(joiner.toString());
		
		builder.append(", accountNonExpired=");
		builder.append(isAccountNonExpired());
		builder.append(", accountNonLocked=");
		builder.append(isAccountNonLocked());
		builder.append(", credentialsNonExpired=");
		builder.append(isCredentialsNonExpired());
		builder.append(", enabled=");
		builder.append(isEnabled());
		builder.append(", firstName=");
		builder.append(getFirstName());
		builder.append(", lastName=");
		builder.append(getLastName());
		builder.append(", birthday=");
		builder.append(getBirthday());
		
		List<MessageEntity> messages = getMessages();
		
		builder.append(", messagesCount=");
		builder.append((messages != null) ? messages.size() : 0);
		
		builder.append(", creationDate=");
		builder.append(getCreationDate());
		builder.append(", lastUpdateDate=");
		builder.append(getLastUpdateDate());
		builder.append("]");
		
		return builder.toString();
	}
}
