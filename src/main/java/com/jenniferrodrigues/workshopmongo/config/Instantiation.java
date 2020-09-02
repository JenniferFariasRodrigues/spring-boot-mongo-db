package com.jenniferrodrigues.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jenniferrodrigues.workshopmongo.domain.Post;
import com.jenniferrodrigues.workshopmongo.domain.User;
import com.jenniferrodrigues.workshopmongo.dto.AuthorDTO;
import com.jenniferrodrigues.workshopmongo.repository.PostRepository;
import com.jenniferrodrigues.workshopmongo.repository.UserRepository;

//springer entender que e configuracao
@Configuration

public class Instantiation implements CommandLineRunner {
	//injeta o userrepository para slavar no banco de dados
	@Autowired
	public UserRepository userRepository;
	//injeta o postrepository para slavar no banco de dados
	@Autowired
	public PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		////instanciando obj do Post
		//Post post1 = new Post(null,sdf.parse("21/03/2018"), "Partiu vida nova!", "Vida nova inicianco em 3,2,1...", maria);
		//Post post2 = new Post(null,sdf.parse("21/03/2018"), "Bom dia!", "Pagina em branco", maria);
		////Salvara no Mongodb
		//userRepository.saveAll(Arrays.asList(maria,alex,bob));
		//postRepository.saveAll(Arrays.asList(post1, post2));
	
		//Utilizando o authorDTO
		//instanciando obj do Post///Salva primeiro
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		Post post1 = new Post(null,sdf.parse("21/03/2018"), "Partiu vida nova!", "Vida nova inicianco em 3,2,1...", new AuthorDTO( maria));
		Post post2 = new Post(null,sdf.parse("21/03/2018"), "Bom dia!", "Pagina em branco", new AuthorDTO(maria));
		//Salvara no Mongodb
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		
		//Referenciando os posts do usuário
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	
	}

}
