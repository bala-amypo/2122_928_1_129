// DiscountApplicationController.java
package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.BundleRule;
import com.example.demo.entity.Cart;
import com.example.demo.entity.DiscountApplication;
import com.example.demo.repository.BundleRuleRepo;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.DiscountApplicationRepo;

@RestController
@RequestMapping("/discounts")
public class DiscountApplicationController {

    private final DiscountApplicationRepo discountRepo;
    private final CartRepo cartRepo;
    private final BundleRuleRepo bundleRuleRepo;

    public DiscountApplicationController(DiscountApplicationRepo discountRepo,
                                         CartRepo cartRepo,
                                         BundleRuleRepo bundleRuleRepo) {
        this.discountRepo = discountRepo;
        this.cartRepo = cartRepo;
        this.bundleRuleRepo = bundleRuleRepo;
    }

    @PostMapping
    public DiscountApplication applyDiscount(@RequestParam Long cartId,
                                             @RequestParam Long ruleId,
                                             @RequestParam BigDecimal amount) {

        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        BundleRule rule = bundleRuleRepo.findById(ruleId)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        DiscountApplication da = new DiscountApplication();
        da.setCart(cart);
        da.setBundleRule(rule);
        da.setDiscountAmount(amount);

        return discountRepo.save(da);
    }
}
