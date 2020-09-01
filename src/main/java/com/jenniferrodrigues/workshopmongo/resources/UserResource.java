package com.jenniferrodrigues.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jenniferrodrigues.workshopmongo.domain.User;
import com.jenniferrodrigues.workshopmongo.dto.UserDTO;
import com.jenniferrodrigues.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	//O controlador REST acessa o servico
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity< List<UserDTO>> findAll() {
		//User maria = new User("1", "Maria Silva","maria@gmail.com");
		//User alex = new User("2", "Alex Costa","alexa@gmail.com");
		//List<User> list= new ArrayList<>();
		//list.addAll(Arrays.asList(maria, alex));
		
		//Utilizando o findAll
		List<User> list = service.findAll();
		
		//converte lista para lista DTO
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
		
		@RequestMapping(value = "/{id}", method=RequestMethod.GET)
		public ResponseEntity<UserDTO> findById(@PathVariable String id) {
			User obj = service.findById(id);
			
			//resposta convertido
			return ResponseEntity.ok().body(new UserDTO(obj));

	
	
	}
}
