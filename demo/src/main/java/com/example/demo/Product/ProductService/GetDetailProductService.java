package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDetailProductService {

    @Autowired
    private ProductHomepage productHomepage;

    public ProductEntity getDetailProduct(Long id)
    {
        return  productHomepage.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " +id));
    }
}
