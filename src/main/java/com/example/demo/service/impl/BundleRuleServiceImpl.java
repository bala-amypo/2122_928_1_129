package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository repository;

    public BundleRuleServiceImpl(BundleRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BundleRule createRule(BundleRule rule) {
        rule.setActive(true);
        return repository.save(rule);
    }

    @Override
    public BundleRule updateRule(Long id, BundleRule rule) {
        BundleRule existing = repository.findById(id).orElseThrow();
        existing.setRuleName(rule.getRuleName());
        existing.setRequiredProductIds(rule.getRequiredProductIds());
        existing.setDiscountPercentage(rule.getDiscountPercentage());
        return repository.save(existing);
    }

    @Override
    public BundleRule getRuleById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<BundleRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public List<BundleRule> getActiveRules() {
        return repository.findAll()
                .stream()
                .filter(BundleRule::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateRule(Long id) {
        BundleRule rule = repository.findById(id).orElseThrow();
        rule.setActive(false);
        repository.save(rule);
    }
}
