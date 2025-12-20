package com.example.demo.service;

import com.example.demo.model.CartItem;

public interface CartItemService {

    CartItem addItemToCart(CartItem item);

    CartItem updateItem(CartItem item);

    void deleteItem(Long id);
}
