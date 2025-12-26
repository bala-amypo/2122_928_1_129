package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(String email, String role, Long userId) {
        // Dummy token for unit tests
        return "dummy-jwt-token";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }
}
