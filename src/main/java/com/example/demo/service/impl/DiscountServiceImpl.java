package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DiscountServiceImpl {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BundleRuleRepository bundleRuleRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountServiceImpl(CartRepository c, CartItemRepository ci,
                               BundleRuleRepository br, DiscountApplicationRepository da) {
        this.cartRepository = c;
        this.cartItemRepository = ci;
        this.bundleRuleRepository = br;
        this.discountApplicationRepository = da;
    }

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId).orElseThrow();
        if (!cart.getActive()) return Collections.emptyList();

        discountApplicationRepository.deleteByCartId(cartId);

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        Set<Long> productIds = items.stream()
                .map(ci -> ci.getProduct().getId())
                .collect(Collectors.toSet());

        List<DiscountApplication> results = new ArrayList<>();

        for (BundleRule rule : bundleRuleRepository.findByActiveTrue()) {
            Set<Long> required = Arrays.stream(rule.getRequiredProductIds().split(","))
                    .map(String::trim)
                    .map(Long::valueOf)
                    .collect(Collectors.toSet());

            if (productIds.containsAll(required)) {
                BigDecimal total = items.stream()
                        .map(ci -> ci.getProduct().getPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(
                        total.multiply(BigDecimal.valueOf(rule.getDiscountPercentage() / 100))
                );

                results.add(discountApplicationRepository.save(app));
            }
        }
        return results;
    }
}
