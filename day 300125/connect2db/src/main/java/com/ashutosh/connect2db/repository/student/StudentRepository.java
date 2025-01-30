package com.ashutosh.connect2db.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashutosh.connect2db.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
