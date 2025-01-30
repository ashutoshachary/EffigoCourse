package com.ashutosh.employee_todo_project.service;

import com.ashutosh.employee_todo_project.model.Employee;
import com.ashutosh.employee_todo_project.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository ;
    private PasswordEncoder passwordEncoder;

    public Employee registerEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public String uploadPhoto(String employeeId, MultipartFile file) {
        try {
            String fileName = employeeId + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/photos");
            Files.createDirectories(uploadPath);
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
            
            Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
            employee.setPhotoUrl("/photos/" + fileName);
            employeeRepository.save(employee);
            
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload photo", e);
        }
    }

    public String uploadResume(String employeeId, MultipartFile file) {
        try {
            String fileName = employeeId + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/resumes");
            Files.createDirectories(uploadPath);
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
            
            Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
            employee.setResumeUrl("/resumes/" + fileName);
            employeeRepository.save(employee);
            
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload resume", e);
        }
    }
}
