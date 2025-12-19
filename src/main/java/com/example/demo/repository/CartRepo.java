package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User;

public interface CartRepo extends JpaRepository<Cart, Long> {

    boolean existsByUser(User user);

    Optional<Cart> findByUser(User user);
}
