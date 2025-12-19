package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cart_id", "bundle_rule_id"})
    }
)
public class DiscountApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bundle_rule_id", nullable = false)
    private BundleRule bundleRule;

    @NotNull
    @DecimalMin(value = "0.01", message = "Discount must be positive")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal discountAmount;

    private LocalDateTime appliedAt;

    // constructors
    public DiscountApplication() {}

    public DiscountApplication(Long id, Cart cart,
                               BundleRule bundleRule,
                               BigDecimal discountAmount) {
        this.id = id;
        this.cart = cart;
        this.bundleRule = bundleRule;
        this.discountAmount = discountAmount;
    }

    @PrePersist
    public void prePersist() {
        appliedAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }

    public Cart getCart() { return cart; }
    public void setCart(Cart cart) { this.cart = cart; }

    public BundleRule getBundleRule() { return bundleRule; }
    public void setBundleRule(BundleRule bundleRule) {
        this.bundleRule = bundleRule;
    }

    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public LocalDateTime getAppliedAt() { return appliedAt; }
}
