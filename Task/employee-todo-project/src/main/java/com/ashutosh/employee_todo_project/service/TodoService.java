package com.ashutosh.employee_todo_project.service;

import com.ashutosh.employee_todo_project.model.Todo;
import com.ashutosh.employee_todo_project.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private TodoRepository todoRepository;

    public List<Todo> getCurrentUserTodos() {
        String employeeId = SecurityContextHolder.getContext().getAuthentication().getName();
        return todoRepository.findByEmployeeId(employeeId);
    }

    public Todo createTodo(Todo todo) {
        String employeeId = SecurityContextHolder.getContext().getAuthentication().getName();
        todo.setEmployeeId(employeeId);
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String id, Todo todoUpdate) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found"));
        
        String employeeId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!todo.getEmployeeId().equals(employeeId)) {
            throw new RuntimeException("Not authorized to update this todo");
        }
        
        todo.setTaskName(todoUpdate.getTaskName());
        todo.setDate(todoUpdate.getDate());
        todo.setCompleted(todoUpdate.isCompleted());
        return todoRepository.save(todo);
    }

    public void deleteTodo(String id) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found"));
        
        String employeeId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!todo.getEmployeeId().equals(employeeId)) {
            throw new RuntimeException("Not authorized to delete this todo");
        }
        
        todoRepository.deleteById(id);
    }
}
