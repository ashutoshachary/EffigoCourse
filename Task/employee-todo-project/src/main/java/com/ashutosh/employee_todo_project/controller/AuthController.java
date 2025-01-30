package com.ashutosh.employee_todo_project.controller;

import com.ashutosh.employee_todo_project.dto.AuthRequest;
import com.ashutosh.employee_todo_project.dto.AuthResponse;
import com.ashutosh.employee_todo_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}