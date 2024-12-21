package com.example.demo.Order.OrderService;

import com.example.demo.Product.ProductRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service

public class CheckQuantityOfProduct {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public boolean purchaseProduct(Long productId, BigDecimal purchaseQuantity) {

        int updatedRows = productRepository.updateProductQuantity(productId, purchaseQuantity);


        return updatedRows > 0;
    }

}
