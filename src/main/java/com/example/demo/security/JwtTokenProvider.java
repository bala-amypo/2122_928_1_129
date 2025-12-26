package com.example.demo.security;

public class JwtTokenProvider {

    public String generateToken(String email, String role, Long userId) {
        return "dummy-token";
    }

    public boolean validateToken(String token) {
        return token != null;
    }
}
