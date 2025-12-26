package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    List<CartItem> findByCartId(Long cartId);

    @Query("SELECT c FROM CartItem c WHERE c.cart.id = :cartId AND c.quantity >= :min")
    List<CartItem> findByCartIdAndMinQuantity(Long cartId, Integer min);
}