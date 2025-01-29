package com.ashutosh.rest.webservices.restful_web_services.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ashutosh.rest.webservices.restful_web_services.todo.repository.TodoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend access
public class TodoJpaResource {

    private final TodoRepository todoRepository;

    public TodoJpaResource(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 1. Retrieve all todos for a user
    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }

    // 2. Retrieve a single todo by ID
    @GetMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> retrieveTodoById(@PathVariable int id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // 3. Create a new todo
    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Todo> createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username); // Ensure username is set
        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    // 4. Update an existing todo
    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo todo) {
        if (!todoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        todo.setId(id); // Ensure ID is set
        Todo updatedTodo = todoRepository.save(todo);
        return ResponseEntity.ok(updatedTodo);
    }

    // 5. Delete a todo
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        if (!todoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
