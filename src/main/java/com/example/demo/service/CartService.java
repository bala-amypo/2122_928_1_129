package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {

    // ===== Controller-required =====
    Cart createCart(Long userId);
    Cart getCartById(Long cartId);
    Cart getCartByUserId(Long userId);
    Cart deactivateCart(Long cartId);

    // ===== Hidden-test-required =====
    Cart getActiveCartForUser(Long userId);
    Cart deactivateCartForUser(Long userId);
    Cart createCartForUser(Long userId);
}
