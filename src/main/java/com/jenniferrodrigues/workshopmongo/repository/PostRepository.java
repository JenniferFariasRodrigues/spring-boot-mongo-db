package com.jenniferrodrigues.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jenniferrodrigues.workshopmongo.domain.Post;

//Possibilita varias operacoes:salvar,deletar... objetos pelo UserRepository
@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	//Consulta simples com @Query
	//?0= pegara a primeira letra,i= nao case sensitive
	@Query( "{ 'title' :{ $regex: ?0, $options: '<i>' } ")
	List<Post> searchTitle(String text);
	
	
	//Consulta simples com query methods
	//criar o método de busca
	//Ignorecase nao faz distincao de textos case sensitive 
	
	List<Post> findByTitleContainingIgnoreCase(String text);

	
	
	
}
