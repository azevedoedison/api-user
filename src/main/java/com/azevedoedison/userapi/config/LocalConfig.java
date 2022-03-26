package com.azevedoedison.userapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.azevedoedison.userapi.model.User;
import com.azevedoedison.userapi.repository.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	
	@Autowired
	private UserRepository repository;

	@Bean
	public void startDB() {
		User u1 = new User(null, "Valdir", "valdir@mail.com", "123");
		User u2 = new User(null, "Edison", "edison@mail.com", "456");
		
		repository.saveAll(List.of(u1,u2));
		
		
	}
	
}
