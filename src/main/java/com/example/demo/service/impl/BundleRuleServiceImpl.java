package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository repository;

    public BundleRuleServiceImpl(BundleRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BundleRule createRule(BundleRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<BundleRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BundleRule rule = repository.findById(id).orElseThrow();
        rule.setActive(false);
        repository.save(rule);
    }
}
