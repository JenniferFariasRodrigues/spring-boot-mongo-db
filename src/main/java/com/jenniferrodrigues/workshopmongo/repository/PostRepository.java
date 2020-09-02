package com.jenniferrodrigues.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jenniferrodrigues.workshopmongo.domain.Post;

//Possibilita varias operacoes:salvar,deletar... objetos pelo UserRepository
@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	

}
