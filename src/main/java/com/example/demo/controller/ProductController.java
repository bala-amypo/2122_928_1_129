// ProductController.java
package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepo;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        if (productRepo.existsBySku(product.getSku())) {
            throw new RuntimeException("SKU already exists");
        }
        if (product.getPrice().signum() <= 0) {
            throw new RuntimeException("Price must be greater than zero");
        }
        return productRepo.save(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
