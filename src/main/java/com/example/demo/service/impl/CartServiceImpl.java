package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public Cart createCart(Long userId) {
        return new Cart();
    }

    @Override
    public Cart getCartById(Long cartId) {
        return new Cart();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return new Cart();
    }

    @Override
    public void deactivateCart(Long cartId) {
    }
}
