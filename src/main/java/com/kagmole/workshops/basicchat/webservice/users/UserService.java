package com.kagmole.workshops.basicchat.webservice.users;

import com.kagmole.workshops.basicchat.webservice.shared.exceptions.EntityNotFoundException;
import com.kagmole.workshops.basicchat.webservice.users.exceptions.UsernameAlreadyTakenException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	private PasswordEncoder passwordEncoder;
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(
			PasswordEncoder passwordEncoder,
			UserRepository userRepository) {
		
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findOneByUsername(username);
		
		if (user == null) {
			
			UsernameNotFoundException exception = new UsernameNotFoundException(username + " not found.");
			
			LOGGER.info("[FAIL]: UserService.loadUserByUsername\n> {}", exception.getMessage());
			
			throw exception;
		}
		
		return user;
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
	
	public UserEntity retrieveByCredentials(String username, String password) throws InvalidGrantException {
		
		UserEntity user = userRepository.findOneByUsername(username);
		
		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			
			InvalidGrantException exception = new InvalidGrantException("Wrong username or password.");
			
			LOGGER.warn("[FAIL] UserService.retrieveByCredentials\n> {}", exception.getMessage());
			
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
