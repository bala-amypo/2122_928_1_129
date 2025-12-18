package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BundleRule;
import com.example.demo.repository.BundleRuleRepo;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepo bundleRuleRepo;

    public BundleRuleServiceImpl(BundleRuleRepo bundleRuleRepo) {
        this.bundleRuleRepo = bundleRuleRepo;
    }

    @Override
    public BundleRule createRule(BundleRule rule) {
        if (rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
            throw new RuntimeException("Invalid discount percentage");
        }
        return bundleRuleRepo.save(rule);
    }

    @Override
    public List<BundleRule> getAllRules() {
        return bundleRuleRepo.findAll();
    }
}
