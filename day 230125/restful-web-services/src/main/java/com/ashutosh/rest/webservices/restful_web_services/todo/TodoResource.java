package com.ashutosh.rest.webservices.restful_web_services.todo;

import java.util.List;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@CrossOrigin(origins = "http://localhost:5173") // Allow frontend access
public class TodoResource {
	
	private final TodoService todoService;
	
	public TodoResource(TodoService todoService) {
		this.todoService = todoService;
	}
	
	// 1. Retrieve all todos for a user
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoService.findByUsername(username);
	}
	
	// 2. Retrieve a single todo by ID
	@GetMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> retrieveTodoById(@PathVariable int id) {
		Todo todo = todoService.findById(id);
		return ResponseEntity.ok(todo);
	}

	// 3. Create a new todo
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Todo> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
	}
	
	// 4. Update an existing todo
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo todo) {
		todoService.updateTodo(todo);
		return ResponseEntity.ok(todo);
	}
	
	// 5. Delete a todo
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
		todoService.deleteById(id);
		return ResponseEntity.noContent().build(); // Returns 204 No Content
	}
}
