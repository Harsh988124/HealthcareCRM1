package com.healthcare.crm.controller;

import com.healthcare.crm.dto.LoginRequest;
import com.healthcare.crm.dto.RegisterRequest;
import com.healthcare.crm.entity.User;
import com.healthcare.crm.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ✅ Register User
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }

    // ✅ Login User (no JWT, returns user details)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        return ResponseEntity.ok(user); // return full user object (id, email, role, etc.)
    }
}
