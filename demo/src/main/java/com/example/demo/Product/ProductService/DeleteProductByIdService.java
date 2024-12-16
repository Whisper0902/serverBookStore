package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductByIdService {

    @Autowired
    private ProductHomepage productHomepage;

    public ProductEntity deleteProductById(Long id) {
        if (id == null) {
            throw new RuntimeException("value is not type Long");
        }
        ProductEntity productEntity = productHomepage.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productHomepage.deleteById(id);
        return productEntity;

    }
}
