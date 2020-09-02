package com.jenniferrodrigues.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jenniferrodrigues.workshopmongo.domain.Post;
import com.jenniferrodrigues.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/pots")
public class PostResource {
	// O controlador REST acessa o servico
	@Autowired
	private PostService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		// resposta convertido
		return ResponseEntity.ok().body(obj);

	}
}
