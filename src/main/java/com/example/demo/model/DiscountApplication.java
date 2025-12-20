// DiscountApplication.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class DiscountApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;
    private double discountAmount;

    public DiscountApplication() {}

    public DiscountApplication(Long cartId, double discountAmount) {
        this.cartId = cartId;
        this.discountAmount = discountAmount;
    }

    public Long getId() { return id; }
    public Long getCartId() { return cartId; }
    public double getDiscountAmount() { return discountAmount; }

    public void setId(Long id) { this.id = id; }
    public void setCartId(Long cartId) { this.cartId = cartId; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }
}
