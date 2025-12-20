// JwtTokenProvider.java
package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(String email, String role, Long userId) {
        return email + ":" + role + ":" + userId;
    }

    public boolean validateToken(String token) {
        return token != null && token.contains(":");
    }
}
