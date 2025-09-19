package com.healthcare.crm.dto;

import com.healthcare.crm.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role; // DOCTOR / PATIENT / ADMIN
}
