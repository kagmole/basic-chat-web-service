package com.kagmole.workshops.basicchat.webservice.users.authorities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum AuthorityType {
	
	ADMINISTRATION;
	
	public static final Set<AuthorityType> ALL;
	
	static {
		
		HashSet<AuthorityType> allImpl = new HashSet<>();
		
		for (AuthorityType authorityType : values()) {
			allImpl.add(authorityType);
		}
		
		ALL = Collections.unmodifiableSet(allImpl);
	}
}
