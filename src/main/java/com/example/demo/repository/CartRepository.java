package com.example.demo.repository;

import com.example.demo.model.Cart;
import java.util.*;

public interface CartRepository {
    Optional<Cart> findByUserIdAndActiveTrue(Long userId);
    Optional<Cart> findById(Long id);
    Cart save(Cart cart);
}
