package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartItemService;

import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository itemRepo;
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartItemServiceImpl(
            CartItemRepository itemRepo,
            CartRepository cartRepo,
            ProductRepository productRepo) {
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public CartItem addItem(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        return itemRepo.save(new CartItem(cart, product, quantity));
    }

    public CartItem updateItem(Long id, int quantity) {
        CartItem item = itemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        item.setQuantity(quantity);
        return itemRepo.save(item);
    }

    public List<CartItem> getItemsForCart(Long cartId) {
        return itemRepo.findByCartId(cartId);
    }

    public void removeItem(Long id) {
        itemRepo.deleteById(id);
    }
}
