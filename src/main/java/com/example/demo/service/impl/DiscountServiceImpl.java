package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BundleRule;
import com.example.demo.model.Cart;
import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.DiscountApplicationRepository;
import com.example.demo.service.DiscountService;

import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final BundleRuleRepository bundleRuleRepository;
    private final CartRepository cartRepository;
    private final DiscountApplicationRepository discountRepo;

    public DiscountServiceImpl(
            BundleRuleRepository bundleRuleRepository,
            CartRepository cartRepository,
            DiscountApplicationRepository discountRepo) {

        this.bundleRuleRepository = bundleRuleRepository;
        this.cartRepository = cartRepository;
        this.discountRepo = discountRepo;
    }

    @Override
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();
        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : rules) {
            DiscountApplication app = new DiscountApplication();
            app.setCart(cart);
            app.setBundleRule(rule);

            // ✅ FIX: use getDiscountPercentage()
            BigDecimal discount = rule.getDiscountPercentage();
            app.setDiscountAmount(discount);

            result.add(discountRepo.save(app));
        }

        return result;
    }

    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountRepo.findByCartId(cartId);
    }
}
