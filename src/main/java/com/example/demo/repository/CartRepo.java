package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
