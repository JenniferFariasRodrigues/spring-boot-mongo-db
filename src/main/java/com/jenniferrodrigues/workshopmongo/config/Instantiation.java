package com.jenniferrodrigues.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jenniferrodrigues.workshopmongo.domain.User;
import com.jenniferrodrigues.workshopmongo.repository.UserRepository;

//springer entender que e configuracao
@Configuration

public class Instantiation implements CommandLineRunner {

	//injeta o userrepository
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//Salvara no Mongodb
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
	}

}
