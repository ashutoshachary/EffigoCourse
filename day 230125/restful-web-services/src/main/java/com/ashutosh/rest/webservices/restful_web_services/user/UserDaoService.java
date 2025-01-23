package com.ashutosh.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	// JPA hibernate -> database
	// UserDaoServiece > Static List
	// public List<User> findAll(){
	// public User save(User user){
	// public User findOne(int id){
	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 0;
	
	static {
		users.add(new User(++userCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Eve",LocalDate.now().minusYears(25)));
		users.add(new User(++userCount,"Jhon",LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll(){
		return users;
	}

	
	
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getIdInteger().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setIdInteger(++userCount);
		users.add(user);
		return user;
	}
	
	

}
