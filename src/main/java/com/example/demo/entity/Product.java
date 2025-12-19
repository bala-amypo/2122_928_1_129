package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "product",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "sku")
    }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "SKU must not be blank")
    @Size(min = 3, max = 50, message = "SKU must be between 3 and 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @NotBlank(message = "Product name must not be blank")
    @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Category must not be blank")
    @Size(min = 2, max = 100, message = "Category must be between 2 and 100 characters")
    @Column(nullable = false)
    private String category;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Active flag must not be null")
    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ---------- Lifecycle Callback ----------

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
