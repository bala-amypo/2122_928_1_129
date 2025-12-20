package com.example.demo.model;

import java.math.BigDecimal;
import java.util.List;

public class BundleRule {

    private Long id;
    private String ruleName;
    private List<Long> requiredProductIds;
    private BigDecimal discountPercentage;
    private boolean active;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public List<Long> getRequiredProductIds() { return requiredProductIds; }
    public void setRequiredProductIds(List<Long> requiredProductIds) {
        this.requiredProductIds = requiredProductIds;
    }

    public BigDecimal getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
