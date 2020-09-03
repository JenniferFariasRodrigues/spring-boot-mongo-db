package com.jenniferrodrigues.workshopmongo.resources;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jenniferrodrigues.workshopmongo.domain.Post;
import com.jenniferrodrigues.workshopmongo.resources.util.URL;
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

	// Consulta simples com query methods
	@RequestMapping(value = "/{titlesearch}", method = RequestMethod.GET)
	//O @RequestParam serve para colocar o ? no campo de busca da URL
	//(value="text", defaultValue="") value="text" para o parametro reconhecer o text no campo URL do Postman
	// defaultValue="" retornara string vazia se o parametro nao for informado
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) throws UnsupportedEncodingException {
		//decodificar o parametro
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		
		// resposta convertido
		return ResponseEntity.ok().body(list);

	}

	// Consulta com varios argumentos
	@RequestMapping(value = "/{fullsearch}", method = RequestMethod.GET)
		//O @RequestParam serve para colocar o ? no campo de busca da URL
		//(value="text", defaultValue="") value="text" para o parametro reconhecer o text no campo URL do Postman
		// defaultValue="" retornara string vazia se o parametro nao for informado
		public ResponseEntity<List<Post>> fullsearch(
				@RequestParam(value="text", defaultValue="") String text, 
				@RequestParam(value="minDate", defaultValue="") String minDate ,
				@RequestParam(value="maxDate", defaultValue="") String maxDate)
						
						
						
				throws UnsupportedEncodingException, ParseException, com.sun.el.parser.ParseException {
			//decodificar o texto
			text = URL.decodeParam(text);
			
			//Caso ocorra um problema aparecera Date(0)=data do sist Java ou Date() data atual do sistema
			Date min = URL.convertDate(minDate, new Date(0));
			Date max = URL.convertDate(maxDate, new Date());
			List<Post> list = service.fullSearch(text, min, max);
			
			// resposta convertido
			return ResponseEntity.ok().body(list);

		}
}
