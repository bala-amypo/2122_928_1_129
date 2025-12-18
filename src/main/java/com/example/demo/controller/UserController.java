// UserController.java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return userRepo.save(user);
    }
}
