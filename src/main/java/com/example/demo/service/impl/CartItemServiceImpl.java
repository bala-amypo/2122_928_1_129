package com.example.demo.service.impl;

import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem addItemToCart(CartItem item) {
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem updateItem(CartItem item) {
        return cartItemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
