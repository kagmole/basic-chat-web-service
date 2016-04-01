package com.kagmole.workshops.basicchat.webservice.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	public long countByUsername(String username);
}
