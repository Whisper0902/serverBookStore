package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class GetProductByFieldTest {
    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private GetProductByFieldService getProductByFieldService;

    Map<String, Object> fieldGenre;
    Map<String, Object> fieldAuthor;
    Map<String, Object> fieldPublisher;
    Map<String,Object> fieldNull;
    List<ProductEntity> listByGenre;
    List<ProductEntity> listByAuthor;
    List<ProductEntity> listByPublisher;

    ProductEntity product1 ;
    ProductEntity product2;
    ProductEntity product3;



    @BeforeEach
    public void setUp(){
        fieldGenre = new HashMap<>();
        fieldGenre.put("genre","Van hoc");

        fieldAuthor = new HashMap<>();
        fieldAuthor.put("author","Nha Nam");

        fieldPublisher = new HashMap<>();
        fieldPublisher.put("publisher","Kim Dong");

        fieldNull = new HashMap<>();
        fieldNull.put(null,null);

        product1 = new ProductEntity();
        product1.setId(1L);
        product1.setTitle("Duong vao tim em");
        product1.setGenre("Van hoc");
        product1.setAuthor("Nha Nam");
        product1.setPublisher("Tuoi tre");

        product2 = new ProductEntity();
        product2.setId(2L);
        product2.setTitle("Con duong mua");
        product2.setGenre("Van hoc");
        product2.setAuthor("Nha Nam");
        product2.setPublisher("Kim Dong");

        product3 = new ProductEntity();
        product3.setId(3L);
        product3.setTitle("Doi la the thoi");
        product3.setGenre("Truyen ngan");
        product3.setAuthor("Nam Cao");
        product3.setPublisher("Kim Dong");

        listByGenre.add(product1);
        listByGenre.add(product2);

        listByAuthor.add(product1);
        listByAuthor.add(product2);

        listByPublisher.add(product2);
        listByPublisher.add(product3);

    }

    @Test
    public void
}
