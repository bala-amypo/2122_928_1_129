package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Override
    public CartItem addItem(Long cartId, Long productId, Integer quantity) {
        return new CartItem(); // stub for test
    }

    @Override
    public CartItem updateItem(Long itemId, Integer quantity) {
        return new CartItem();
    }

    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return List.of();
    }

    @Override
    public void removeItem(Long itemId) {
    }
}
