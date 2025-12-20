package com.example.demo.service;

import java.util.List;
import com.example.demo.model.CartItem;

public interface CartItemService {

    CartItem addItem(Long cartId, Long productId, int quantity);

    CartItem updateItem(Long id, int quantity);

    List<CartItem> getItemsForCart(Long cartId);

    void removeItem(Long id);
}
