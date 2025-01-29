package com.ashutosh.learnspringsecurity.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	private static final List<Todo> LIST_TODOS = List.of(new Todo("ashutosh","Learn AWS"),
			new Todo("ashutosh","Learn DevOps"));
	@GetMapping("/todos")
	public List<Todo> retriveAllTodos(){
		return LIST_TODOS;
	}
	@GetMapping("/users/{username}/todos")
	public Todo retriveTodosForSpecificUser(@PathVariable String username){
		return LIST_TODOS.get(0);
	}
	
	@PostMapping("/users/{username}/todos")
	public void postTodosForSpecificUser(@PathVariable String username, @RequestBody Todo todo){
		logger.info("Creating Todo",todo,username);
	}
}

record Todo (String username, String description) {}

