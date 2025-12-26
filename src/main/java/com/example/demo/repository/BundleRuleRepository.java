package com.example.demo.repository;

import com.example.demo.model.BundleRule;
import java.util.*;

public interface BundleRuleRepository {
    List<BundleRule> findByActiveTrue();
}
