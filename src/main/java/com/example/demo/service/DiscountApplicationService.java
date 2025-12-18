package com.example.demo.service;

import java.math.BigDecimal;
import com.example.demo.entity.DiscountApplication;

public interface DiscountApplicationService {

    DiscountApplication applyDiscount(Long cartId, Long ruleId, BigDecimal amount);
}
