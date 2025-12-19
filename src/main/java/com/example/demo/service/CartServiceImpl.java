package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CartRepo;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public Cart createCart(Long userId) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }

        if (cartRepo.existsByUserId(userId)) {
            throw new IllegalArgumentException("Cart already exists for this user");
        }

        Cart cart = new Cart();
        cart.setUserId(userId);

        return cartRepo.save(cart);
    }

    @Override
    public Cart getCartByUserId(Long userId) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }

        return cartRepo.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found for userId: " + userId
                        )
                );
    }
}
