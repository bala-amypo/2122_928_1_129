// CartItem.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;
    private Long productId;
    private Integer quantity;

    public CartItem() {}

    public CartItem(Long cartId, Long productId, Integer quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public Long getCartId() { return cartId; }
    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }

    public void setId(Long id) { this.id = id; }
    public void setCartId(Long cartId) { this.cartId = cartId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
