package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product createProduct(Product product) {
        if (productRepo.existsBySku(product.getSku())) {
            throw new RuntimeException("SKU already exists");
        }
        if (product.getPrice().signum() <= 0) {
            throw new RuntimeException("Price must be positive");
        }
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
