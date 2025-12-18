// CartItemController.java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartItemRepo;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.ProductRepo;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemRepo cartItemRepo;
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;

    public CartItemController(CartItemRepo cartItemRepo,
                              CartRepo cartRepo,
                              ProductRepo productRepo) {
        this.cartItemRepo = cartItemRepo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    @PostMapping
    public CartItem addItem(@RequestParam Long cartId,
                            @RequestParam Long productId,
                            @RequestParam Integer quantity) {

        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be positive");
        }

        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartItemRepo.save(item);
    }
}
