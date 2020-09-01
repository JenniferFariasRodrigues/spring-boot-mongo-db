package com.jenniferrodrigues.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jenniferrodrigues.workshopmongo.domain.User;

//Possibilita varias operacoes:salvar,deletar... objetos pelo UserRepository
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	

}
