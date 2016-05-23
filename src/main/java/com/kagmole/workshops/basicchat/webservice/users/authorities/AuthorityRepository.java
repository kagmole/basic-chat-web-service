package com.kagmole.workshops.basicchat.webservice.users.authorities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Integer> {
	
	public AuthorityEntity findOneByAuthority(String authority);
}
