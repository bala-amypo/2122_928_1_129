package com.example.demo.repository;

import com.example.demo.model.Product;
import java.util.*;

public interface ProductRepository {
    Optional<Product> findBySku(String sku);
    Optional<Product> findById(Long id);
    Product save(Product product);
}
