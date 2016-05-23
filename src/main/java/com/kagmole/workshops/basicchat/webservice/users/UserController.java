package com.kagmole.workshops.basicchat.webservice.users;

import com.google.common.collect.Iterables;
import com.kagmole.workshops.basicchat.webservice.shared.exceptions.ValidationFailedException;
import com.kagmole.workshops.basicchat.webservice.shared.miscellaneous.ValidationGroup.Create;
import com.kagmole.workshops.basicchat.webservice.shared.miscellaneous.ValidationGroup.Update;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private PasswordEncoder passwordEncoder;
	
	private UserService userService;
	
	@Autowired
	public UserController(
			PasswordEncoder passwordEncoder,
			UserService userService) {
		
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
	}
	
	/**
	 * @since	v1.0.0
	 * @version	v1.0.0
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<UserEntity> retrieveAll() {
		
		LOGGER.debug("[ENTERING] UserController.retrieveAll");
		
		Iterable<UserEntity> users = userService.retrieveAll();
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("[SUCCESS] UserController.retrieveAll\n"
					+ "usersCount={}",
					Iterables.size(users));
		}
		
		return users;
	}
	
	/**
	 * @since	v1.0.0
	 * @version	v1.0.0
	 */
	@RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.GET)
	public UserEntity retrieve(
			@PathVariable Integer userId) {
		
		LOGGER.debug("[ENTERING] UserController.retrieve\n"
				+ "userId={}",
				userId);
		
		UserEntity user = userService.retrieve(userId);
		
		LOGGER.debug("[SUCCESS] UserController.retrieve\n"
				+ "user={}",
				user);
		
		return user;
	}
	
	/**
	 * @since	v1.0.0
	 * @version	v1.0.0
	 */
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserEntity create(
			@RequestBody @Validated(Create.class) UserForm userForm,
			BindingResult result) {
		
		LOGGER.debug("[ENTERING] UserController.create\n"
				+ "userForm={}",
				userForm);
		
		if (result.hasErrors()) {
			ValidationFailedException exception = new ValidationFailedException(result);
			
			LOGGER.info("[FAIL]: UserController.create\n{}", exception.getMessage());
			
			throw exception;
		}
		
		UserEntity user = new UserEntity();
		
		user.setUsername(userForm.getUsername());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setBirthday(userForm.getBirthday());
		
		user.setMessages(Collections.emptyList());
		
		user = userService.create(user);
		
		LOGGER.debug("[SUCCESS] UserController.create\n"
				+ "user={}",
				user);
		
		return user;
	}
	
	/**
	 * @since	v1.0.0
	 * @version	v1.0.0
	 */
	@PreAuthorize("#principalUser.userId == #userId or hasAuthority('ADMINISTRATION')")
	@RequestMapping(
			value = "/{userId:\\d+}", method = RequestMethod.PATCH,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserEntity update(
			@AuthenticationPrincipal UserEntity principalUser,
			@PathVariable Integer userId,
			@RequestBody @Validated(Update.class) UserForm userForm,
			BindingResult result) {
		
		LOGGER.debug("[ENTERING] UserController.update\n"
				+ "userId={}\n"
				+ "userForm={}",
				userId, userForm);
		
		if (result.hasErrors()) {
			ValidationFailedException exception = new ValidationFailedException(result);
			
			LOGGER.info("[FAIL]: UserController.update\n{}", exception.getMessage());
			
			throw exception;
		}
		
		UserEntity user = userService.retrieve(userId);
		
		if (userForm.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		}
		
		if (userForm.getFirstName() != null) {
			user.setFirstName(userForm.getFirstName());
		}
		
		if (userForm.getLastName() != null) {
			user.setLastName(userForm.getLastName());
		}
		
		if (userForm.getBirthday() != null) {
			user.setBirthday(userForm.getBirthday());
		}
		
		user = userService.update(user);
		
		LOGGER.debug("[SUCCESS] UserController.update\n"
				+ "user={}",
				user);
		
		return user;
	}
	
	/**
	 * @since	v1.0.0
	 * @version	v1.0.0
	 */
	@PreAuthorize("#principalUser.userId == #userId or hasAuthority('ADMINISTRATION')")
	@RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.DELETE)
	public void delete(
			@AuthenticationPrincipal UserEntity principalUser,
			@PathVariable Integer userId) {
		
		LOGGER.debug("[ENTERING] UserController.delete\n"
				+ "userId={}",
				userId);
		
		UserEntity user = userService.retrieve(userId);
		
		userService.delete(user);
		
		LOGGER.debug("[SUCCESS] UserController.delete\n"
				+ "user={}",
				user);
	}
}
