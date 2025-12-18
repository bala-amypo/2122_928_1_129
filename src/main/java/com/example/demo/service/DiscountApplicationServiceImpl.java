package com.example.demo.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BundleRule;
import com.example.demo.entity.Cart;
import com.example.demo.entity.DiscountApplication;
import com.example.demo.repository.BundleRuleRepo;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.DiscountApplicationRepo;

@Service
public class DiscountApplicationServiceImpl implements DiscountApplicationService {

    private final DiscountApplicationRepo discountRepo;
    private final CartRepo cartRepo;
    private final BundleRuleRepo bundleRuleRepo;

    public DiscountApplicationServiceImpl(DiscountApplicationRepo discountRepo,
                                          CartRepo cartRepo,
                                          BundleRuleRepo bundleRuleRepo) {
        this.discountRepo = discountRepo;
        this.cartRepo = cartRepo;
        this.bundleRuleRepo = bundleRuleRepo;
    }

    @Override
    public DiscountApplication applyDiscount(Long cartId,
                                             Long ruleId,
                                             BigDecimal amount) {

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
