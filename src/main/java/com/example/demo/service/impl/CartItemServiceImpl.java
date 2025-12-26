package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CartItemServiceImpl {

    private final CartItemRepository itemRepo;
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartItemServiceImpl(CartItemRepository i, CartRepository c, ProductRepository p) {
        this.itemRepo = i;
        this.cartRepo = c;
        this.productRepo = p;
    }

    public CartItem addItemToCart(CartItem item) {

        if (item.getQuantity() <= 0)
            throw new IllegalArgumentException("Quantity");

        Cart cart = cartRepo.findById(item.getCart().getId())
                .orElseThrow(EntityNotFoundException::new);

        if (!cart.getActive())
            throw new IllegalArgumentException("active carts");

        Product product = productRepo.findById(item.getProduct().getId())
                .orElseThrow(EntityNotFoundException::new);

        CartItem existing =
                itemRepo.findByCartIdAndProductId(cart.getId(), product.getId())
                        .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
            return itemRepo.save(existing);
        }

        item.setCart(cart);
        item.setProduct(product);
        return itemRepo.save(item);
    }

    public List<CartItem> getItemsForCart(Long cartId) {
        return itemRepo.findByCartId(cartId);
    }
}
