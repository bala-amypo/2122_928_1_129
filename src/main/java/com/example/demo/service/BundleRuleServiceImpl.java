package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BundleRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BundleRuleRepo;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepo bundleRuleRepo;

    public BundleRuleServiceImpl(BundleRuleRepo bundleRuleRepo) {
        this.bundleRuleRepo = bundleRuleRepo;
    }

    @Override
    public BundleRule createRule(BundleRule rule) {

        if (rule == null) {
            throw new IllegalArgumentException("BundleRule cannot be null");
        }

        if (rule.getDiscountPercentage() == null ||
            rule.getDiscountPercentage() < 0 ||
            rule.getDiscountPercentage() > 100) {

            throw new IllegalArgumentException(
                    "Discount percentage must be between 0 and 100"
            );
        }

        return bundleRuleRepo.save(rule);
    }

    @Override
    public List<BundleRule> getAllRules() {
        return bundleRuleRepo.findAll();
    }

    @Override
    public BundleRule getRuleById(Long id) {
        return bundleRuleRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BundleRule not found with id: " + id
                        )
                );
    }
}
