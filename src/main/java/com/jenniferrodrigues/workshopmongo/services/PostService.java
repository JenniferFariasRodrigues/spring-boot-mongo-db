package com.jenniferrodrigues.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jenniferrodrigues.workshopmongo.domain.Post;
import com.jenniferrodrigues.workshopmongo.repository.PostRepository;
import com.jenniferrodrigues.workshopmongo.services.exception.ObjectNotFoundException;

//Obtendo um post por id
@Service
public class PostService {
	// O controlador acessa o repositorio
	// instanciar automaticamente um objeto=autowired
	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	//Consulta simples com query methods
	//criar o método de busca
	public List<Post> findByTitle(String text){
		//return repo.findByTitleContaining(text);
		
		//Consulta simples com @Query
		//Para testar no Postman
		//http://localhost:8081/posts/titlesearch?text=bom%dia!
		return repo.searchTitle(text);
	}
	
	
	//Consulta com varios criterios
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		//adiciona 1 dia paa ler certinho
		maxDate = new Date(maxDate.getTime()+ 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
		
	}

}
