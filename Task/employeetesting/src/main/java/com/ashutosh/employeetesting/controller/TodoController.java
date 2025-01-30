package com.ashutosh.employeetesting.controller;

import com.ashutosh.employeetesting.model.Todo;
import com.ashutosh.employeetesting.repository.TodoRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees/{employeeId}/todos")
@RequiredArgsConstructor
public class TodoController {
    
    private final TodoRepository todoRepository;
    @Autowired  // Inject TodoRepository via constructor
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    @GetMapping
    public List<Todo> getEmployeeTodos(@PathVariable String employeeId) {
        return todoRepository.findByEmployeeId(employeeId);
    }
    
    @PostMapping
    public Todo createTodo(@PathVariable String employeeId, @Valid @RequestBody Todo todo) {
        todo.setEmployeeId(employeeId);
        return todoRepository.save(todo);
    }
    
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable String employeeId, @PathVariable String todoId) {
        return (ResponseEntity<Todo>) todoRepository.findById(todoId)
            .map(todo -> {
                if (todo.getEmployeeId().equals(employeeId)) {
                    return ResponseEntity.ok(todo);
                }
                return ResponseEntity.notFound().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String employeeId,
            @PathVariable String todoId,
            @Valid @RequestBody Todo todo) {
        return (ResponseEntity<Todo>) todoRepository.findById(todoId)
            .map(existingTodo -> {
                if (!existingTodo.getEmployeeId().equals(employeeId)) {
                    return ResponseEntity.notFound().build();
                }
                todo.setId(todoId);
                todo.setEmployeeId(employeeId);
                return ResponseEntity.ok(todoRepository.save(todo));
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String employeeId, @PathVariable String todoId) {
        return todoRepository.findById(todoId)
            .map(todo -> {
                if (todo.getEmployeeId().equals(employeeId)) {
                    todoRepository.delete(todo);
                    return ResponseEntity.ok().<Void>build();
                }
                return ResponseEntity.notFound().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
