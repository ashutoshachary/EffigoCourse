package com.ashutosh.employee_todo_project.controller;

import com.ashutosh.employee_todo_project.model.Employee;
import com.ashutosh.employee_todo_project.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.registerEmployee(employee));
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<String> uploadPhoto(
            @PathVariable String id,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(employeeService.uploadPhoto(id, file));
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<String> uploadResume(
            @PathVariable String id,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(employeeService.uploadResume(id, file));
    }
}
