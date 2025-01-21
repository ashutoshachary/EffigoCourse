package com.ashutosh.springboot.learn_jpa_and_hybernet.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ashutosh.springboot.learn_jpa_and_hybernet.course.jdbc.CourseJdbcRepository;
import com.ashutosh.springboot.learn_jpa_and_hybernet.course.jpa.CourseJpaRepository;
import com.ashutosh.springboot.learn_jpa_and_hybernet.course.springdatajpa.CourseSpringDataJpaRepository;
@Component
public class CourseCommandLineRunner implements CommandLineRunner{
	
	
//	@Autowired
//	private CourseJdbcRepository reposeitory;
//	@Autowired
//	private CourseJpaRepository reposeitory;
	
	@Autowired
	private CourseSpringDataJpaRepository reposeitory;
	

	@Override
	public void run(String... args) throws Exception {
		reposeitory.save(new Course(1,"Learn AWS Now!", "Udemy"));
		reposeitory.save(new Course(2,"Learn Azure Now!", "Udemy"));
		reposeitory.save(new Course(3,"Learn c++!", "Udemy"));
		
		reposeitory.deleteById(1l);
		
		System.out.println(reposeitory.findById(2l));
		System.out.println(reposeitory.findById(3l));
		
		System.out.println(reposeitory.findAll());
		
		System.out.println(reposeitory.count());
		
		System.out.println(reposeitory.findByAuthor("Udemy"));
		
		System.out.println(reposeitory.findByName("Learn c++!"));
		
		
		
		
		
		
	}
	
	

}
