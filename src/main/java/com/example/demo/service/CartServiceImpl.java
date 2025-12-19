package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.UserRepo;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final UserRepo userRepo;

    public CartServiceImpl(CartRepo cartRepo, UserRepo userRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Cart createCart(Long userId) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        )
                );

        if (cartRepo.existsByUser(user)) {
            throw new IllegalArgumentException("Cart already exists for this user");
        }

        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepo.save(cart);
    }

    @Override
    public Cart getCartByUserId(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        )
                );

        return cartRepo.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found for user id: " + userId
                        )
                );
    }
}
