package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import java.util.List;

public class CartItemServiceImpl {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartRepository c, ProductRepository p, CartItemRepository ci) {
        this.cartRepository = c;
        this.productRepository = p;
        this.cartItemRepository = ci;
    }

    public CartItem addItemToCart(CartItem item) {
        Cart cart = cartRepository.findById(item.getCart().getId())
                .orElseThrow();

        if (!cart.getActive())
            throw new IllegalArgumentException("Only active carts allowed");

        if (item.getQuantity() <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow();

        return cartItemRepository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + item.getQuantity());
                    return cartItemRepository.save(existing);
                })
                .orElseGet(() -> cartItemRepository.save(item));
    }

    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}
