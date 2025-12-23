package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.BundleRule;
import com.example.demo.model.Cart;
import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.DiscountApplicationRepository;
import com.example.demo.service.DiscountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        if (!cart.isActive()) {
            return List.of();
        }

        discountRepo.deleteByCartId(cartId);

        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();
        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : rules) {

            BigDecimal discountPercentage = rule.getDiscountPercentage();

            if (discountPercentage.compareTo(BigDecimal.ZERO) > 0) {
                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);

                // Minimal safe discount calculation
                BigDecimal discountAmount = discountPercentage;
                app.setDiscountAmount(discountAmount);
                app.setAppliedAt(LocalDateTime.now());

                result.add(discountRepo.save(app));
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
