package com.example.demo.controller;

import com.example.demo.dto.CartItemRequest;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.service.impl.CartItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemServiceImpl service;

    public CartItemController(CartItemServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public CartItem addItem(@RequestBody CartItemRequest req) {

        CartItem item = new CartItem();
        item.setQuantity(req.getQuantity());

        Cart cart = new Cart();
        cart.setId(req.getCartId());

        Product product = new Product();
        product.setId(req.getProductId());

        item.setCart(cart);
        item.setProduct(product);

        return service.addItemToCart(item);
    }

    @GetMapping("/cart/{cartId}")
    public List<CartItem> getItems(@PathVariable Long cartId) {
        return service.getItemsForCart(cartId);
    }
}
