package com.kagmole.workshops.basicchat.webservice.messages;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
}
