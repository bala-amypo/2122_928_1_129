package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "discount_application",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cart_id", "bundle_rule_id"})
    }
)
public class DiscountApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Cart must not be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @NotNull(message = "Bundle rule must not be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bundle_rule_id", nullable = false)
    private BundleRule bundleRule;

    @NotNull(message = "Discount amount must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount amount must be greater than zero")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal discountAmount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime appliedAt;

    // ---------- Lifecycle Callback ----------

    @PrePersist
    public void prePersist() {
        this.appliedAt = LocalDateTime.now();
    }

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public BundleRule getBundleRule() {
        return bundleRule;
    }

    public void setBundleRule(BundleRule bundleRule) {
        this.bundleRule = bundleRule;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }
}
