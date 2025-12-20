// CartServiceImpl.java
package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Long userId) {
        if (cartRepository.findByUserId(userId).isPresent()) {
            throw new IllegalArgumentException("Cart already exists");
        }
        return cartRepository.save(new Cart(userId));
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public void deactivateCart(Long id) {
        Cart cart = getCartById(id);
        cart.setActive(false);
        cartRepository.save(cart);
    }
}
