package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruleName")
})
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleName;

    // CSV of product IDs (e.g. "1,2,3")
    private String requiredProductIds;

    private Double discountPercentage;
    private Boolean active;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getRequiredProductIds() { return requiredProductIds; }
    public void setRequiredProductIds(String requiredProductIds) {
        this.requiredProductIds = requiredProductIds;
    }

    public Double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
