package com.kagmole.workshops.basicchat.webservice.users;

import com.kagmole.workshops.basicchat.webservice.shared.exceptions.EntityNotFoundException;
import com.kagmole.workshops.basicchat.webservice.users.exceptions.UsernameAlreadyTakenException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Iterable<UserEntity> retrieveAll() {
		
		return userRepository.findAll();
	}
	
	public UserEntity retrieve(Integer userId) throws EntityNotFoundException {
		
		UserEntity user = userRepository.findOne(userId);
		
		if (user == null) {
			EntityNotFoundException exception = new EntityNotFoundException(UserEntity.class, userId);
			
			LOGGER.info("[FAIL]: UserService.retrieve\n> {}", exception.getMessage());
			
			throw exception;
		}
		
		return user;
	}
	
	public UserEntity create(UserEntity user) throws UsernameAlreadyTakenException {
		
		if (userRepository.countByUsername(user.getUsername()) != 0L) {
			UsernameAlreadyTakenException exception = new UsernameAlreadyTakenException(user.getUsername());
			
			LOGGER.info("[FAIL]: UserService.create\n> {}", exception.getMessage());
			
			throw exception;
		}
		
		return userRepository.save(user);
	}
	
	public UserEntity update(UserEntity user) {
		
		return userRepository.save(user);
	}
	
	public void delete(UserEntity user) {
		
		userRepository.delete(user);
	}
}
