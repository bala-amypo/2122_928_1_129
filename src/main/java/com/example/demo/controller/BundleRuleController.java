// BundleRuleController.java
package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.BundleRule;
import com.example.demo.repository.BundleRuleRepo;

@RestController
@RequestMapping("/bundle-rules")
public class BundleRuleController {

    private final BundleRuleRepo bundleRuleRepo;

    public BundleRuleController(BundleRuleRepo bundleRuleRepo) {
        this.bundleRuleRepo = bundleRuleRepo;
    }

    @PostMapping
    public BundleRule create(@RequestBody BundleRule rule) {
        if (rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
            throw new RuntimeException("Invalid discount");
        }
        return bundleRuleRepo.save(rule);
    }

    @GetMapping
    public List<BundleRule> getAll() {
        return bundleRuleRepo.findAll();
    }
}
