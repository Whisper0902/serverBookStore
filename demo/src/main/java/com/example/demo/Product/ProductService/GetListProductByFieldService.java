package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GetListProductByFieldService {


    @Autowired
    private ProductHomepage productHomepage;


    public List<ProductEntity> getProductByField(Map<String, String> fieldSearch) {


        List<ProductEntity> listResultProduct = new ArrayList<>();
        for (Map.Entry<String, String> entry : fieldSearch.entrySet()) {
            String keyName = entry.getKey();
            String value = entry.getValue();
            if (keyName == null || value == null) {
                throw new RuntimeException("Value input is null");
            }

            switch (keyName) {
                case "genre":
                    listResultProduct = productHomepage.findListProductByGenre(value);
                    break;
                case "author":
                    listResultProduct = productHomepage.findListProductByAuthor(value);
                    break;
                case "publisher":
                    listResultProduct = productHomepage.findListProductByPublisher(value);
                    break;
            }
        }

        if (listResultProduct.isEmpty()) {
            throw new RuntimeException("Not found product by fieldSearch");
        }
        return listResultProduct;

    }
}
