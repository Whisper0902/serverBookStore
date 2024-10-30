package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GetProductByFieldService {


    @Autowired
    private BookRepository bookRepository;


    public List<ProductEntity> getProductByField(Map<String,Object> field)
    {
        List<ProductEntity> listProduct = new ArrayList<>();
        for(Map.Entry<String,Object> entry : field.entrySet())
        {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key){
                case "genre":
                    String genre = (String) value;
                    listProduct = bookRepository.findAllByGenre(genre);
                    break;
                case "author":
                    String author = (String) value;
                    listProduct = bookRepository.findAllByAuthor(author);
                    break;
                case "publisher":
                    String publisher = (String) value;
                    listProduct = bookRepository.findAllByPublisher(publisher);
                    break;
            }
        }
        return listProduct;


    }
}
