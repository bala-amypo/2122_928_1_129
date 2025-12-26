package com.example.demo.repository;

import com.example.demo.model.DiscountApplication;
import java.util.*;

public interface DiscountApplicationRepository {
    void deleteByCartId(Long cartId);
    DiscountApplication save(DiscountApplication app);
    List<DiscountApplication> findByCartId(Long cartId);
}
