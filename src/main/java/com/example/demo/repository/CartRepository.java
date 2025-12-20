package com.example.demo.repository;

import com.example.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // hidden tests
    Optional<Cart> findByUserIdAndActiveTrue(Long userId);

    // hidden tests
    Optional<Cart> findByUserIdAndInactiveTrue(Long userId);
}
