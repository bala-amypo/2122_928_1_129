package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);
}
