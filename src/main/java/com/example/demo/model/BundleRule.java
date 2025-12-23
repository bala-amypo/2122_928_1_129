package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long buyProductId;
    private int buyQuantity;

    private Long freeProductId;
    private int freeQuantity;

    private boolean active = true;

    public BundleRule() {}

    public Long getId() {
        return id;
    }

    public Long getBuyProductId() {
        return buyProductId;
    }

    public void setBuyProductId(Long buyProductId) {
        this.buyProductId = buyProductId;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Long getFreeProductId() {
        return freeProductId;
    }

    public void setFreeProductId(Long freeProductId) {
        this.freeProductId = freeProductId;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }

    public void setFreeQuantity(int freeQuantity) {
        this.freeQuantity = freeQuantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
