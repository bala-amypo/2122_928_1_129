package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getActiveCartForUser(Long userId) {
        return cartRepository
                .findByUserIdAndActiveTrue(userId)
                .orElseGet(() -> createCartForUser(userId));
    }

    @Override
    public Cart deactivateCartForUser(Long userId) {
        Cart cart = cartRepository
                .findByUserIdAndActiveTrue(userId)
                .orElseThrow();

        cart.setActive(false);
        return cartRepository.save(cart);
    }

    @Override
    public Cart createCartForUser(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setActive(true);
        return cartRepository.save(cart);
    }
}
