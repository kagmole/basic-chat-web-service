package com.kagmole.workshops.basicchat.webservice.messages;

import com.google.common.collect.Iterables;
import com.kagmole.workshops.basicchat.webservice.shared.exceptions.ValidationFailedException;
import com.kagmole.workshops.basicchat.webservice.shared.miscellaneous.ValidationGroup.Create;
import com.kagmole.workshops.basicchat.webservice.users.UserEntity;
import com.kagmole.workshops.basicchat.webservice.users.UserService;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
	
	private MessageService messageService;
	
	private UserService userService;
	
	@Autowired
	public MessageController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}
	
	/**
	 * @since	v1.0.0
	 * @version	v1.0.0
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<MessageEntity> retrieveAll(
			@RequestParam(required = false) Long after) {
		
		LOGGER.debug("[ENTERING] MessageController.retrieveAll\n"
				+ "since={}",
				after);
		
		Iterable<MessageEntity> messages = null;
		
		if (after == null) {
			messages = messageService.retrieveAll();
		} else {
			Instant afterDate = Instant.ofEpochMilli(after);
			
			messages = messageService.retrieveAllAfter(afterDate);
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("[SUCCESS] MessageController.retrieveAll\n"
					+ "messagesCount={}",
					Iterables.size(messages));
		}
		
		return messages;
	}
	
	/**
	 * @since	v1.0.0
	 * @version	v1.0.0
	 */
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageEntity create(
			@RequestParam Integer userId,
			@RequestBody @Validated(Create.class) MessageForm messageForm,
			BindingResult result) {
		
		LOGGER.debug("[ENTERING] MessageController.create\n"
				+ "userId={}\n"
				+ "messageForm={}",
				userId, messageForm);
		
		if (result.hasErrors()) {
			ValidationFailedException exception = new ValidationFailedException(result);
			
			LOGGER.info("[FAIL]: MessageController.create\n{}", exception.getMessage());
			
			throw exception;
		}
		
		UserEntity author = userService.retrieve(userId);
		
		MessageEntity message = new MessageEntity();
		
		message.setAuthor(author);
		message.setContent(messageForm.getContent());
		
		message = messageService.create(message);
		
		LOGGER.debug("[SUCCESS] MessageController.create\n"
				+ "message={}",
				message);
		
		return message;
	}
}
