package com.example.RoomRentingSystem.controllers;

import com.example.RoomRentingSystem.dto.CustomerDTO;
import com.example.RoomRentingSystem.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerDTO customerDTO) {
        authService.register(customerDTO);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomerDTO customerDTO) {
        String token = authService.login(customerDTO.getEmail(), customerDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> adminLogin(@RequestBody CustomerDTO adminDTO) {
        if ("admin@example.com".equals(adminDTO.getEmail()) && "admin123".equals(adminDTO.getPassword())) {
            return ResponseEntity.ok(authService.generateAdminToken());
        }
        return ResponseEntity.status(401).body("Invalid admin credentials");
    }
}
