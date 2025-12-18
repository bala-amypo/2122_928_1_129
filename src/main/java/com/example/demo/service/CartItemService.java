package com.example.demo.service;

import com.example.demo.entity.CartItem;

public interface CartItemService {

    CartItem addItem(Long cartId, Long productId, Integer quantity);
}
