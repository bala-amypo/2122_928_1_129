// CartItemServiceImpl.java
package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;

public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem addItem(Long cartId, Long productId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        return cartItemRepository.save(new CartItem(cartId, productId, quantity));
    }

    @Override
    public CartItem updateItem(Long id, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public void removeItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
