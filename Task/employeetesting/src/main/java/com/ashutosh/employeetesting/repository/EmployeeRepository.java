package com.ashutosh.employeetesting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ashutosh.employeetesting.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findByEmail(String email);
}
