package com.ashutosh.springboot.first_jsp_app.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(1, "ashutosh" , "Learn AWS"
				, LocalDate.now().plusYears(1), false));
		todos.add(new Todo(2, "ashutosh" , "Learn devops"
				, LocalDate.now().plusYears(2), false));
		todos.add(new Todo(1, "ashutosh" , "Learn Fulstack"
				, LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username){
		return todos;
	}

}
