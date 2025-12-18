package com.example.demo.service;

import com.example.demo.entity.Cart;

public interface CartService {

    Cart createCart(Long userId);

    Cart getCartByUserId(Long userId);
}
