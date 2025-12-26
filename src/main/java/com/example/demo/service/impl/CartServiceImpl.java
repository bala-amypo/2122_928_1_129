package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;

public class CartServiceImpl {

    private final CartRepository repo;

    public CartServiceImpl(CartRepository repo) {
        this.repo = repo;
    }

    public Cart createCart(Long userId) {
        return repo.findByUserIdAndActiveTrue(userId)
                .orElseGet(() -> repo.save(new Cart() {{
                    setUserId(userId);
                    setActive(true);
                }}));
    }

    public Cart getActiveCartForUser(Long userId) {
        return repo.findByUserIdAndActiveTrue(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Active cart not found"));
    }
}
