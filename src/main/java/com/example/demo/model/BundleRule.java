// File: BundleRule.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "bundle_rules")
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleName;

    @ElementCollection
    @CollectionTable(
        name = "bundle_rule_products",
        joinColumns = @JoinColumn(name = "bundle_rule_id")
    )
    @Column(name = "product_id")
    private List<Long> requiredProductIds;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discountPercentage;

    private boolean active;

    public BundleRule() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<Long> getRequiredProductIds() {
        return requiredProductIds;
    }

    public void setRequiredProductIds(List<Long> requiredProductIds) {
        this.requiredProductIds = requiredProductIds;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
