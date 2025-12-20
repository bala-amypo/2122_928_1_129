package com.example.demo.repository;

import com.example.demo.model.DiscountApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountApplicationRepository
        extends JpaRepository<DiscountApplication, Long> {

    void deleteByCartId(Long cartId);
}
