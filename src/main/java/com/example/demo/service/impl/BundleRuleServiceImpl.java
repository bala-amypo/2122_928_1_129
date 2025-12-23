package com.example.demo.service.impl;

import java.util.List;
import java.math.BigDecimal;


import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository bundleRuleRepository;

    public BundleRuleServiceImpl(BundleRuleRepository bundleRuleRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
    }

    @Override
    public BundleRule createRule(BundleRule rule) {

        if (rule.getDiscountPercentage() == null ||
            rule.getDiscountPercentage().compareTo(BigDecimal.ZERO) < 0 ||
            rule.getDiscountPercentage().compareTo(BigDecimal.valueOf(100)) > 0) {

            throw new IllegalArgumentException("between 0 and 100");
        }

        rule.setActive(true);
        return bundleRuleRepository.save(rule);
    }

    @Override
    public BundleRule updateRule(Long id, BundleRule rule) {

        BundleRule existing = bundleRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BundleRule not found"));

        existing.setBuyProductId(rule.getBuyProductId());
        existing.setBuyQuantity(rule.getBuyQuantity());
        existing.setFreeProductId(rule.getFreeProductId());
        existing.setFreeQuantity(rule.getFreeQuantity());
        existing.setDiscountPercentage(rule.getDiscountPercentage());
        existing.setActive(rule.isActive());

        return bundleRuleRepository.save(existing);
    }

    @Override
    public BundleRule getRuleById(Long id) {
        return bundleRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BundleRule not found"));
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
