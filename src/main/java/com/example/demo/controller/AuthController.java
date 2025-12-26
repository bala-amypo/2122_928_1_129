package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // In real apps → validate user via DB
        // For now → mock successful authentication

        String email = request.getEmail();
        String role = "USER";
        Long userId = 1L;

        String token = jwtTokenProvider.generateToken(email, role, userId);

        return new AuthResponse(token, userId, email);
    }
}
