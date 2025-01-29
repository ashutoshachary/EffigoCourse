package com.ashutosh.rest.webservices.restful_web_services.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashutosh.rest.webservices.restful_web_services.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

	List<Todo> findByUsername(String username);
	
}
