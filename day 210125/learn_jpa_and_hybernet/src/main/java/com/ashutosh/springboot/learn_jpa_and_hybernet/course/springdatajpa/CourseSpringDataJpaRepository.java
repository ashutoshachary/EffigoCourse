package com.ashutosh.springboot.learn_jpa_and_hybernet.course.springdatajpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashutosh.springboot.learn_jpa_and_hybernet.course.Course;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long>{

	List<Course> findByAuthor(String author);
	List<Course> findByName(String name);
}
