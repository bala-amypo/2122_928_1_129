package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CartItem;

public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long cartId);

    CartItem findByCartIdAndProductId(Long cartId, Long productId); // 🔴 REQUIRED

    List<CartItem> findByCartIdAndQuantityGreaterThanEqual(
            Long cartId, int quantity); // 🔴 REQUIRED
}
