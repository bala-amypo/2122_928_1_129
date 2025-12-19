package com.example.demo.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BundleRule;
import com.example.demo.entity.Cart;
import com.example.demo.entity.DiscountApplication;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BundleRuleRepo;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.DiscountApplicationRepo;

@Service
public class DiscountApplicationServiceImpl implements DiscountApplicationService {

    private final DiscountApplicationRepo discountRepo;
    private final CartRepo cartRepo;
    private final BundleRuleRepo bundleRuleRepo;

    public DiscountApplicationServiceImpl(
            DiscountApplicationRepo discountRepo,
            CartRepo cartRepo,
            BundleRuleRepo bundleRuleRepo) {

        this.discountRepo = discountRepo;
        this.cartRepo = cartRepo;
        this.bundleRuleRepo = bundleRuleRepo;
    }

    @Override
    public DiscountApplication applyDiscount(
            Long cartId,
            Long ruleId,
            BigDecimal amount) {

        if (cartId == null || cartId <= 0) {
            throw new IllegalArgumentException("Cart ID must be a positive number");
        }

        if (ruleId == null || ruleId <= 0) {
            throw new IllegalArgumentException("Rule ID must be a positive number");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Discount amount must be greater than zero");
        }

        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found with id: " + cartId
                        )
                );

        BundleRule rule = bundleRuleRepo.findById(ruleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BundleRule not found with id: " + ruleId
                        )
                );

        DiscountApplication discountApplication = new DiscountApplication();
        discountApplication.setCart(cart);
        discountApplication.setBundleRule(rule);
        discountApplication.setDiscountAmount(amount);

        return discountRepo.save(discountApplication);
    }
}
