package com.kagmole.workshops.basicchat.webservice.users.authorities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kagmole.workshops.basicchat.webservice.shared.exceptions.EntityNotFoundException;

@Service
public class AuthorityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityService.class);
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public AuthorityEntity retrieveByBusinessKey(String authority)
			throws EntityNotFoundException {
		
		AuthorityEntity authorityEntity = authorityRepository.findOneByAuthority(authority);
		
		if (authorityEntity == null) {
			
			EntityNotFoundException exception = new EntityNotFoundException(AuthorityEntity.class, authority);
			
			LOGGER.info("[FAIL]: AuthorityService.retrieveByBusinessKey\n> {}", exception.getMessage());
			
			throw exception;
		}
		
		return authorityEntity;
	}
}
