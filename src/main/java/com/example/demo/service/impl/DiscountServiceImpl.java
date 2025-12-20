// DiscountServiceImpl.java
package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BundleRule;
import com.example.demo.model.CartItem;
import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.DiscountApplicationRepository;

import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final BundleRuleRepository bundleRuleRepository;
    private final CartItemRepository cartItemRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountServiceImpl(
            BundleRuleRepository bundleRuleRepository,
            CartItemRepository cartItemRepository,
            DiscountApplicationRepository discountApplicationRepository) {

        this.bundleRuleRepository = bundleRuleRepository;
        this.cartItemRepository = cartItemRepository;
        this.discountApplicationRepository = discountApplicationRepository;
    }

    @Override
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();

        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : rules) {
            result.add(
                discountApplicationRepository.save(
                    new DiscountApplication(cartId, rule.getDiscount())
                )
            );
        }
        return result;
    }

    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}
