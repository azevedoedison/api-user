package com.azevedoedison.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.azevedoedison.userapi.model.User;

@SpringBootApplication
public class UserapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserapiApplication.class, args);
		
		User user = new User(1,"edison","azevedoedison@gmail.com","123");
	}

}
