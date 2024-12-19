package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductService {
    @Autowired
    private ProductHomepage productHomepage;

    public ProductEntity updateProduct(ProductEntity product)
    {
        if(product.getId() == null || product.getId() <= 0)
        {
            throw new RuntimeException("Value of product is null");
        }

        ProductEntity resultProduct = productHomepage.findById(product.getId()).orElseThrow(() -> new EntityNotFoundException("Product with ID " + product.getId() + " not found"));

        resultProduct.setId(product.getId());
        resultProduct.setDescription(product.getDescription());
        resultProduct.setAuthor(product.getAuthor());
        resultProduct.setTitle(product.getTitle());
        resultProduct.setGenre(product.getGenre());
        resultProduct.setPrice(product.getPrice());
        resultProduct.setQuantity(product.getQuantity());
        resultProduct.setUrlImage(product.getUrlImage());

        ProductEntity productUpdate = productHomepage.save(resultProduct);
        return productUpdate;

    }


}
