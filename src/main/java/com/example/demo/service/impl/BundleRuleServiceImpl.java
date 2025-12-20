package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;

import org.springframework.stereotype.Service;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository repo;

    public BundleRuleServiceImpl(BundleRuleRepository repo) {
        this.repo = repo;
    }

    public BundleRule createRule(BundleRule rule) {
        return repo.save(rule);
    }

    public BundleRule updateRule(Long id, BundleRule rule) {
        BundleRule r = getRuleById(id);
        r.setRuleName(rule.getRuleName());
        r.setRequiredProductIds(rule.getRequiredProductIds());
        r.setDiscountPercentage(rule.getDiscountPercentage());
        return repo.save(r);
    }

    public BundleRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<BundleRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    public void deactivateRule(Long id) {
        BundleRule r = getRuleById(id);
        r.setActive(false);
        repo.save(r);
    }
}
