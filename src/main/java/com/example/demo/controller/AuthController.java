package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // -------- REGISTER --------
    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {
        User saved = userService.registerUser(user);
        return new AuthResponse(
                saved.getId(),
                saved.getEmail(),
                saved.getRole(),
                "User registered successfully"
        );
    }

    // -------- LOGIN --------
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = userService.getUserByEmail(request.getEmail());

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        if (!user.getActive()) {
            throw new IllegalArgumentException("User is inactive");
        }

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                "Login successful"
        );
    }
}
