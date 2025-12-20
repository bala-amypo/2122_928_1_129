// DiscountService.java
package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DiscountApplication;

public interface DiscountService {

    List<DiscountApplication> evaluateDiscounts(Long cartId);

    DiscountApplication getApplicationById(Long id);

    List<DiscountApplication> getApplicationsForCart(Long cartId);
}
