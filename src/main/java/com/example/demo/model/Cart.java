package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private boolean active = true;

    public Cart() {}

    public Cart(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // 🔴 REQUIRED
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean getActive() {   // 🔴 REQUIRED
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
