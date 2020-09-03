package com.jenniferrodrigues.workshopmongo.repository;

import java.util.Date;
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

	//Consulta com varios criterios
	//Buscar posts contendo um dado string em qualquer lugar (no título, corpo ou comentarios) e em um dado
	//intervalo de datas. Dentro do and  Buscar ou no texto ou no titulo ou nos comentarios
	//{date: {$gte: ?1} compara data. Compara maior ou igual: com a data minima det pelo Post minDate campo 1
	//{date: { $lte: ?2}  compara data:compara menor ou igual: pelo maxDate campo2
	//{ 'comments.text' :{ $regex: ?0, $options: '<i>' } faz a procura do texto dentro dos comentarios no campo 1 sem case sensitive i.
	
	@Query(" { $and: [ {date: {$gte: ?1} }, {date: { $lte: ?2} } , { $or: [ { 'title' :{ $regex: ?0, $options: '<i>' } , { 'body' :{ $regex: ?0, $options: '<i>' } , { 'comments.text' :{ $regex: ?0, $options: '<i>' }  ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
	
}
