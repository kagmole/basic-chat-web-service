package com.kagmole.workshops.basicchat.webservice.messages;

import java.time.Instant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
	
	Iterable<MessageEntity> findByCreationDateAfter(Instant afterDate);
}
