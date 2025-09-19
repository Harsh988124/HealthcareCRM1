package com.healthcare.crm.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public String me(@AuthenticationPrincipal UserDetails user) {
        return "Logged in as: " + user.getUsername();
    }
}
