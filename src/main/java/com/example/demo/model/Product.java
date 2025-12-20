package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String name;

    private BigDecimal price;

    private boolean active = true;

    // ===== REQUIRED BY TESTS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // 🔴 REQUIRED
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean getActive() {   // 🔴 NOT isActive()
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
