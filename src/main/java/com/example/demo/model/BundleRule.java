// BundleRule.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productIds; // CSV
    private double discount;
    private boolean active = true;

    public BundleRule() {}

    public BundleRule(String productIds, double discount) {
        this.productIds = productIds;
        this.discount = discount;
    }

    public Long getId() { return id; }
    public String getProductIds() { return productIds; }
    public double getDiscount() { return discount; }
    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setProductIds(String productIds) { this.productIds = productIds; }
    public void setDiscount(double discount) { this.discount = discount; }
    public void setActive(boolean active) { this.active = active; }
}
