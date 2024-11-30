package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class GetProductById {

    @Autowired
    private ProductHomepage productHomepage;


    public ProductEntity getProductById(Long id)
    {
        return  productHomepage.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));

    }
}
