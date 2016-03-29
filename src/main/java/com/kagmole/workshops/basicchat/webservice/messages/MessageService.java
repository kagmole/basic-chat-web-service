package com.kagmole.workshops.basicchat.webservice.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	private MessageRepository messageRepository;
	
	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public Iterable<MessageEntity> retrieveAll() {
		
		return messageRepository.findAll();
	}
	
	public MessageEntity create(MessageEntity message) {
		
		return messageRepository.save(message);
	}
}
