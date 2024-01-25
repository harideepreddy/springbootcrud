package com.webservice.Restful.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservice.Restful.user.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserResorce2 {

	
	private UserRepository repository;
		
		public UserResorce2(UserRepository repository) {
			this.repository = repository;
		}

		@GetMapping("/jpa/users")
		public List<User> getAllUsers(){
			return repository.findAll();
		}
		
		@GetMapping("/jpa/users/{id}")
		public EntityModel<User> getSpecificUser(@PathVariable Integer id){
			Optional<User> user =  repository.findById(id);
			if(user.isEmpty()) 
				throw new UserNotFoundException("id:"+id);
			

			EntityModel<User> entityModel = EntityModel.of(user.get());
			
			WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
			
			entityModel.add(link.withRel("all-users"));

			return entityModel;
			
		}
		
		@PostMapping("/jpa/users")
		public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
			User saveUser = repository.save(user);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(saveUser.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		}
		
		@DeleteMapping("/jpa/users/{id}")
		public void deleteSpecificUser(@PathVariable Integer id){
			repository.deleteById(id);
		}
		
}
