package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountApplicationRepository discountRepo;
    private final BundleRuleRepository bundleRuleRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public DiscountServiceImpl(
            DiscountApplicationRepository discountRepo,
            BundleRuleRepository bundleRuleRepository,
            CartRepository cartRepository,
            CartItemRepository cartItemRepository) {

        this.discountRepo = discountRepo;
        this.bundleRuleRepository = bundleRuleRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        if (!cart.isActive()) {
            return List.of();
        }

        discountRepo.deleteByCartId(cartId);

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();

        Set<Long> productIdsInCart = items.stream()
                .map(i -> i.getProduct().getId())
                .collect(Collectors.toSet());

        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : rules) {
            String[] requiredIds = rule.getRequiredProductIds().split(",");

            boolean matches = true;
            for (String id : requiredIds) {
                if (!productIdsInCart.contains(Long.parseLong(id.trim()))) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                BigDecimal total = items.stream()
                        .map(i -> i.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(i.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal discountAmount = total
                        .multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
                        .divide(BigDecimal.valueOf(100));

                if (discountAmount.compareTo(BigDecimal.ZERO) > 0) {
                    DiscountApplication app = new DiscountApplication();
                    app.setCart(cart);
                    app.setBundleRule(rule);
                    app.setDiscountAmount(discountAmount);
                    app.setAppliedAt(LocalDateTime.now());

                    result.add(discountRepo.save(app));
                }
            }
        }

        return result;
    }

    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DiscountApplication not found"));
    }

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountRepo.findByCartId(cartId);
    }
}
