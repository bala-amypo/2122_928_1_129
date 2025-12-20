// DiscountServiceImpl.java
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BundleRule;
import com.example.demo.model.CartItem;
import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.DiscountApplicationRepository;

public class DiscountServiceImpl implements DiscountService {

    private final BundleRuleRepository bundleRuleRepository;
    private final CartItemRepository cartItemRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    // Constructor order EXACT
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

        List<DiscountApplication> results = new ArrayList<>();

        for (BundleRule rule : rules) {
            DiscountApplication app =
                    discountApplicationRepository.save(
                            new DiscountApplication(cartId, rule.getDiscount()));
            results.add(app);
        }
        return results;
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
