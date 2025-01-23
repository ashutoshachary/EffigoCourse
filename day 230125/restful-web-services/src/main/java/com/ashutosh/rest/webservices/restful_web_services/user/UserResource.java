package com.ashutosh.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	// Get /users
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
		
	}
	
	@GetMapping("/users/{id}")
	public User retrieveOneUser(@PathVariable int id){
		User user = service.findOne(id);
		
		if(user==null) {
			throw new UserNotFoundException("id: "+id);
		}
		return user;
		
	}
	
	// POST User
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getIdInteger())
				.toUri(); 
		
		return ResponseEntity.created(location).build();
		
	}

}
