package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
    name = "bundle_rule",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruleName")
    }
)
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Rule name must not be blank")
    @Size(min = 3, max = 100, message = "Rule name must be between 3 and 100 characters")
    @Column(nullable = false, unique = true)
    private String ruleName;

    /**
     * CSV of product IDs (e.g. "1,2,3")
     */
    @NotBlank(message = "Required product IDs must not be blank")
    @Pattern(
        regexp = "^\\d+(,\\d+)*$",
        message = "Product IDs must be comma-separated numbers (e.g. 1,2,3)"
    )
    @Column(nullable = false)
    private String requiredProductIds;

    @NotNull(message = "Discount percentage must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount must be greater than 0")
    @DecimalMax(value = "100.0", message = "Discount cannot exceed 100")
    @Column(nullable = false)
    private Double discountPercentage;

    @NotNull(message = "Active flag must not be null")
    @Column(nullable = false)
    private Boolean active;

    // ---------- Getters & Setters ----------

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

    public String getRequiredProductIds() {
        return requiredProductIds;
    }

    public void setRequiredProductIds(String requiredProductIds) {
        this.requiredProductIds = requiredProductIds;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
