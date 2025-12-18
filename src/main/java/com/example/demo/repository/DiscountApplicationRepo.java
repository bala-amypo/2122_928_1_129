package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Cart;
import com.example.demo.entity.DiscountApplication;

public interface DiscountApplicationRepo
        extends JpaRepository<DiscountApplication, Long> {

    List<DiscountApplication> findByCart(Cart cart);
}
