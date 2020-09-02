package com.jenniferrodrigues.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jenniferrodrigues.workshopmongo.domain.User;
import com.jenniferrodrigues.workshopmongo.dto.UserDTO;
import com.jenniferrodrigues.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	// O controlador REST acessa o servico
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		// User maria = new User("1", "Maria Silva","maria@gmail.com");
		// User alex = new User("2", "Alex Costa","alexa@gmail.com");
		// List<User> list= new ArrayList<>();
		// list.addAll(Arrays.asList(maria, alex));

		// Utilizando o findAll
		List<User> list = service.findAll();

		// converte lista para lista DTO
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

	// @RequestMapping(value = "/{id}", method=RequestMethod.GET)
	// public ResponseEntity<UserDTO> findById(@PathVariable String id) {
	// User obj = service.findById(id);
	// resposta convertido
	// return ResponseEntity.ok().body(new UserDTO(obj));

	// Para metodo POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		// transforma para DTO
		User obj = service.fromDTO(objDto);
		// insere no banco de dados
		obj = service.insert(obj);
		// resposta convertido
		// Pega o endereco do novo obj inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// Retorna resposta vazia com codigo 201 e localizacao do novo recurso criado
		return ResponseEntity.created(uri).build();

	}

	// End point para deletar por id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		// resposta convertido
		return ResponseEntity.noContent().build();
	}
	
	//Implementacao update do Atualização de usuário com PUT
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		//garante que o obj tem o Id da requisicao
		obj.setId(id);
		obj=service.update(obj);
		// resposta convertido
		return ResponseEntity.noContent().build();
	}


}
