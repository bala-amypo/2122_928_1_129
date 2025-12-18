package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.BundleRule;

public interface BundleRuleService {

    BundleRule createRule(BundleRule rule);

    List<BundleRule> getAllRules();
}
