// BundleRuleServiceImpl.java
package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;

public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository bundleRuleRepository;

    public BundleRuleServiceImpl(BundleRuleRepository bundleRuleRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
    }

    @Override
    public BundleRule createRule(BundleRule rule) {
        if (rule.getDiscount() <= 0) {
            throw new IllegalArgumentException("Invalid discount");
        }
        return bundleRuleRepository.save(rule);
    }

    @Override
    public BundleRule updateRule(Long id, BundleRule rule) {
        BundleRule existing = getRuleById(id);
        existing.setProductIds(rule.getProductIds());
        existing.setDiscount(rule.getDiscount());
        return bundleRuleRepository.save(existing);
    }

    @Override
    public BundleRule getRuleById(Long id) {
        return bundleRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<BundleRule> getActiveRules() {
        return bundleRuleRepository.findByActiveTrue();
    }

    @Override
    public void deactivateRule(Long id) {
        BundleRule rule = getRuleById(id);
        rule.setActive(false);
        bundleRuleRepository.save(rule);
    }
}
