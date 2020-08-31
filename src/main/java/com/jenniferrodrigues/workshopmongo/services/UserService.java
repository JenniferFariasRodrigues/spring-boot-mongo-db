package com.jenniferrodrigues.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jenniferrodrigues.workshopmongo.domain.User;
import com.jenniferrodrigues.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	//O controlador acessa o repositorio
	// instanciar automaticamente um objeto=autowired
	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();

	}
}
