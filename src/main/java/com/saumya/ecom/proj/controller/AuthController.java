package com.saumya.ecom.proj.controller;

import com.saumya.ecom.proj.dto.AuthResponse;
import com.saumya.ecom.proj.dto.LoginRequest;
import com.saumya.ecom.proj.dto.RegisterRequest;
import com.saumya.ecom.proj.repository.UserRepo;
import com.saumya.ecom.proj.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // ?? cio
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
