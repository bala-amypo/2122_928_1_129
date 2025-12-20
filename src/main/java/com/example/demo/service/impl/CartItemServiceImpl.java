package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartItemService;

import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository itemRepo;
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartItemServiceImpl(
            CartItemRepository itemRepo,
            CartRepository cartRepo,
            ProductRepository productRepo) {
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

@Override
public CartItem addItemToCart(CartItem item) {
    return cartItemRepository.save(item);
}

@Override
public CartItem updateItem(CartItem item) {
    return cartItemRepository.save(item);
}

@Override
public void deleteItem(Long id) {
    cartItemRepository.deleteById(id);
}


    public List<CartItem> getItemsForCart(Long cartId) {
        return itemRepo.findByCartId(cartId);
    }
    

}
