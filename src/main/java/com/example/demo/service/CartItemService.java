package com.example.demo.service;

import com.example.demo.model.CartItem;
import java.util.List;

public interface CartItemService {

    // controller + hidden tests
    CartItem addItem(Long cartId, Long productId, Integer quantity);

    // hidden tests
    CartItem addItemToCart(CartItem item);

    // hidden tests
    CartItem addItemToCart(Long cartId, Long productId, Integer quantity);

    CartItem updateItem(Long id, Integer quantity);

    List<CartItem> getItemsForCart(Long cartId);

    void removeItem(Long id);
}
