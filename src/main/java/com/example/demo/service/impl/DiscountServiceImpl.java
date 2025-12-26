package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class DiscountServiceImpl {

    private final DiscountApplicationRepository appRepo;
    private final BundleRuleRepository ruleRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;

    public DiscountServiceImpl(
            DiscountApplicationRepository a,
            BundleRuleRepository b,
            CartRepository c,
            CartItemRepository i) {
        this.appRepo = a;
        this.ruleRepo = b;
        this.cartRepo = c;
        this.itemRepo = i;
    }

    @Transactional
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepo.findById(cartId).orElse(null);
        if (cart == null || !cart.getActive()) return List.of();

        appRepo.deleteByCartId(cartId);

        List<CartItem> items = itemRepo.findByCartId(cartId);
        List<BundleRule> rules = ruleRepo.findByActiveTrue();

        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : rules) {
            String[] ids = rule.getRequiredProductIds().split(",");
            boolean match = Arrays.stream(ids)
                    .allMatch(id ->
                            items.stream().anyMatch(i ->
                                    i.getProduct().getId().toString().equals(id)));

            if (match) {
                BigDecimal total = items.stream()
                        .map(i -> i.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(i.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal discount =
                        total.multiply(BigDecimal.valueOf(rule.getDiscountPercentage() / 100));

                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(discount);
                app.setAppliedAt(LocalDateTime.now());

                result.add(appRepo.save(app));
            }
        }
        return result;
    }
}
