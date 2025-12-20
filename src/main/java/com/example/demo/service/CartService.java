package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {

    Cart createCart(Long userId);

    Cart getCartById(Long cartId);

    Cart getCartByUserId(Long userId);

    void deactivateCart(Long cartId);
}
