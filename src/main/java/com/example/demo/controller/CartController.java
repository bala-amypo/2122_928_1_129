// CartController.java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepo;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartRepo cartRepo;

    public CartController(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @PostMapping("/{userId}")
    public Cart create(@PathVariable Long userId) {
        if (cartRepo.existsByUserId(userId)) {
            throw new RuntimeException("Cart already exists");
        }
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartRepo.save(cart);
    }

    @GetMapping("/{userId}")
    public Cart getByUser(@PathVariable Long userId) {
        return cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
