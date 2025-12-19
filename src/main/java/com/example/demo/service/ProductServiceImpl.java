package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product createProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (product.getSku() == null || product.getSku().isBlank()) {
            throw new IllegalArgumentException("SKU must not be blank");
        }

        if (productRepo.existsBySku(product.getSku())) {
            throw new IllegalArgumentException("SKU already exists");
        }

        if (product.getPrice() == null || product.getPrice().signum() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive number");
        }

        return productRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        )
                );
    }
}
