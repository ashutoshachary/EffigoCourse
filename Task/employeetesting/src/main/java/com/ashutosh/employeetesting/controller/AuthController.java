//package com.ashutosh.employeetesting.controller;
//
//import com.ashutosh.employeetesting.model.Employee;
//import com.ashutosh.employeetesting.repository.EmployeeRepository;
//import com.ashutosh.employeetesting.security.JwtUtil;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//	@Autowired
//    private EmployeeRepository employeeRepository;
//	@Autowired
//    private PasswordEncoder passwordEncoder;
//	@Autowired
//    private JwtUtil jwtUtil;
//
//    public AuthController(EmployeeRepository employeeRepository, JwtUtil jwtUtil) {
//        this.employeeRepository = employeeRepository;
////        this.passwordEncoder = passwordEncoder;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Employee employee) {
////        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//        Employee savedEmployee = employeeRepository.save(employee);
//        return ResponseEntity.ok(savedEmployee);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
//        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(credentials.get("email"));
//
//        if (optionalEmployee.isPresent() && passwordEncoder.matches(credentials.get("password"), optionalEmployee.get().getPassword())) {
//            Employee employee = optionalEmployee.get();
//            String token = jwtUtil.generateToken(employee.getEmail());
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("id", employee.getId());
//            response.put("email", employee.getEmail());
//            response.put("name", employee.getEmployeeName());
//            response.put("token", token);
//
//            return ResponseEntity.ok(response);
//        }
//
//        return ResponseEntity.badRequest().body("Invalid credentials");
//    }
//}
