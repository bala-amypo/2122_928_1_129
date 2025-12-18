package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepo;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public Cart createCart(Long userId) {
        if (cartRepo.existsByUserId(userId)) {
            throw new RuntimeException("Cart already exists for user");
        }
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartRepo.save(cart);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
