package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {

    Cart getActiveCartForUser(Long userId);

    Cart deactivateCartForUser(Long userId);

    Cart createCartForUser(Long userId);
}
