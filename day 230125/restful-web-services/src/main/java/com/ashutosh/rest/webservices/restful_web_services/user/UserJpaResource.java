package com.ashutosh.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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

import com.ashutosh.rest.webservices.restful_web_services.jpa.PostRepository;
import com.ashutosh.rest.webservices.restful_web_services.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	// Get /users
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	public UserJpaResource( UserRepository repository, PostRepository postRepository) {
		
		this.userRepository = repository;
		this.postRepository = postRepository;
		
	}
	
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
		
	}
	
	// Entity model
	//WebMVCLinkBuilder
	
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id: "+id);
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		entityModel.add(link.withRel("all-users"));
		return entityModel;
		
	}
	
	@GetMapping("/jpa/users/{userId}/posts/{postId}")
	public EntityModel<Post> retrieveOnePost(@PathVariable int userId, @PathVariable int postId) {
	    Optional<User> user = userRepository.findById(userId);
	    if (user.isEmpty()) {
	        throw new UserNotFoundException("User ID: " + userId + " not found.");
	    }

	    Optional<Post> post = postRepository.findById(postId);
	    if (post.isEmpty() || !post.get().getUser().getId().equals(userId)) {
	        throw new PostNotFoundException("Post ID: " + postId + " not found for User ID: " + userId);
	    }

	    EntityModel<Post> entityModel = EntityModel.of(post.get());
	    WebMvcLinkBuilder allPostsLink = linkTo(methodOn(this.getClass()).retievePostsForUser(userId));
	    WebMvcLinkBuilder allUsersLink = linkTo(methodOn(this.getClass()).retrieveAllUsers());
	    
	    entityModel.add(allPostsLink.withRel("all-posts-for-user"));
	    entityModel.add(allUsersLink.withRel("all-users"));

	    return entityModel;
	}

	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retievePostsForUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id : "+ id);
		}
		
		return user.get().getPosts();
		
	}
	
	// POST User
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri(); 
		
		return ResponseEntity.created(location).build();
		
	}

	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsForUser(@PathVariable int id,@Valid @RequestBody Post post){
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id : "+ id);
		}
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri(); 
		
		return ResponseEntity.created(location).build();
		
	}
}
