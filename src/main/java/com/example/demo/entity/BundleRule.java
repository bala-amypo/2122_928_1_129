package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String ruleName;

    @NotBlank
    private String requiredProductIds;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double discountPercentage;

    @NotNull
    private Boolean active;

    @OneToMany(mappedBy = "bundleRule")
    private List<DiscountApplication> appliedDiscounts;

    // constructors
    public BundleRule() {}

    public BundleRule(Long id, String ruleName, String requiredProductIds,
                      Double discountPercentage, Boolean active) {
        this.id = id;
        this.ruleName = ruleName;
        this.requiredProductIds = requiredProductIds;
        this.discountPercentage = discountPercentage;
        this.active = active;
    }

    // getters & setters
    public Long getId() { return id; }
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
