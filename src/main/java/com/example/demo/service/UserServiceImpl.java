package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email must not be blank");
        }

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        return userRepo.save(user);
    }
}
