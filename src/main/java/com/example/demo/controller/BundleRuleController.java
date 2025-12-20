// BundleRuleController.java
package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.BundleRule;
import com.example.demo.service.BundleRuleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/bundle-rules")
@Tag(name = "Bundle Rules")
public class BundleRuleController {

    private final BundleRuleService bundleRuleService;

    public BundleRuleController(BundleRuleService bundleRuleService) {
        this.bundleRuleService = bundleRuleService;
    }

    @PostMapping
    public BundleRule create(@RequestBody BundleRule rule) {
        return bundleRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public BundleRule update(@PathVariable Long id, @RequestBody BundleRule rule) {
        return bundleRuleService.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public BundleRule getById(@PathVariable Long id) {
        return bundleRuleService.getRuleById(id);
    }

    @GetMapping("/active")
    public List<BundleRule> getActive() {
        return bundleRuleService.getActiveRules();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        bundleRuleService.deactivateRule(id);
    }
}
