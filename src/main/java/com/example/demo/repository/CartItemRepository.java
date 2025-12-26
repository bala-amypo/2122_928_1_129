package com.example.demo.repository;

import com.example.demo.model.CartItem;
import java.util.*;

public interface CartItemRepository {
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
    List<CartItem> findByCartId(Long cartId);
    List<CartItem> findByCartIdAndMinQuantity(Long cartId, int minQty);
    CartItem save(CartItem item);
}
