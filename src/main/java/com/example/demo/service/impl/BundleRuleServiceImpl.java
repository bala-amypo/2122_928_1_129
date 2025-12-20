package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.BundleRule;
import com.example.demo.service.BundleRuleService;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    @Override
    public BundleRule createRule(BundleRule rule) {
        return rule;
    }

    @Override
    public BundleRule updateRule(Long id, BundleRule rule) {
        return rule;
    }

    @Override
    public BundleRule getRuleById(Long id) {
        return new BundleRule();
    }

    @Override
    public List<BundleRule> getActiveRules() {
        return List.of();
    }

    @Override
    public void deactivateRule(Long id) {
    }
}
