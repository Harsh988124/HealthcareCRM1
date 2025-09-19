package com.healthcare.crm.service;

import com.healthcare.crm.dto.LoginRequest;
import com.healthcare.crm.dto.RegisterRequest;
import com.healthcare.crm.entity.User;
import com.healthcare.crm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Register new user
    public User register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // store encrypted
        user.setRole(request.getRole()); // e.g. DOCTOR, PATIENT, ADMIN
        return userRepository.save(user);
    }

    // ✅ Login user (validate credentials)
    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return user; // Login successful
        }

        throw new RuntimeException("Invalid email or password");
    }

}

