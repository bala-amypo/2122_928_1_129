package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BundleRule;

public interface BundleRuleRepo extends JpaRepository<BundleRule, Long> {

    Optional<BundleRule> findByRuleName(String ruleName);

    boolean existsByRuleName(String ruleName);
}
