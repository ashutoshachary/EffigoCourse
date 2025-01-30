package com.ashutosh.employee_todo_project.repository;

import com.ashutosh.employee_todo_project.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {
    List<Todo> findByEmployeeId(String employeeId);
}
