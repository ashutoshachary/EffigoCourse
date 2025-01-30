package com.ashutosh.employee_todo_project.service;

import com.ashutosh.employee_todo_project.dto.AuthRequest;
import com.ashutosh.employee_todo_project.dto.AuthResponse;
import com.ashutosh.employee_todo_project.model.Employee;
import com.ashutosh.employee_todo_project.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private  EmployeeRepository employeeRepository;
    private  PasswordEncoder passwordEncoder;
    private  JwtService jwtService;
    private  AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest request) {
        Employee employee = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = User.builder()
                .username(employee.getEmail())
                .password(employee.getPassword())
                .roles("USER")
                .build();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, employee.getId());
    }
}