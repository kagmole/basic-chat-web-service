package com.kagmole.workshops.basicchat.webservice;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kagmole.workshops.basicchat.webservice.users.UserEntity;
import com.kagmole.workshops.basicchat.webservice.users.UserRepository;
import com.kagmole.workshops.basicchat.webservice.users.authorities.AuthorityEntity;
import com.kagmole.workshops.basicchat.webservice.users.authorities.AuthorityRepository;
import com.kagmole.workshops.basicchat.webservice.users.authorities.AuthorityType;

@SpringBootApplication
public class BasicChatWebServiceApplication {
	
	@Bean
	public CommandLineRunner checkServerDatabaseSanityRunner(
			AuthorityRepository authorityRepository,
			PasswordEncoder passwordEncoder,
			UserRepository userRepository) {
		
		return events -> {
			
			// Are all required authorities present?
			AuthorityType.ALL.forEach(authorityType -> {
				
				AuthorityEntity authorityEntity = authorityRepository.findOneByAuthority(authorityType.name());
				
				if (authorityEntity == null) {
					
					authorityEntity = new AuthorityEntity();
					
					authorityEntity.setAuthority(authorityType.name());
					
					authorityRepository.save(authorityEntity);
				}
			});
			
			// Is there at least one administrator?
			if (userRepository.count() == 0L) {
				
				UserEntity user = new UserEntity();
				
				user.setUsername("admin");
				user.setPassword(passwordEncoder.encode("admin"));
				
				HashSet<AuthorityEntity> authorities = new HashSet<>();
				
				authorities.add(authorityRepository.findOneByAuthority(
						AuthorityType.ADMINISTRATION.name()));
				
				user.setAuthorities(authorities);
				
				userRepository.save(user);
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BasicChatWebServiceApplication.class, args);
	}
}
