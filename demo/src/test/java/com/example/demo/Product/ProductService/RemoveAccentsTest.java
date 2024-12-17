package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RemoveAccentsTest {

    private RemoveAccents removeAccents;
    private SearchEntity resultSearchEntity;
    private ProductEntity product;
    private SearchEntity inputSearchEntity;

    @BeforeEach
    public void setUp(){
        product = new ProductEntity();
        product.setId(1L);
        product.setTitle("Đi qua mùa nhớ");
        product.setGenre("Văn học");
        product.setPublisher("Nhã nam");
        product.setAuthor("Văn Cao");
        product.setDescription("Vì nỗi nhớ đong đầy");


        resultSearchEntity = new SearchEntity();
        resultSearchEntity.setBook_id(product);
        resultSearchEntity.setTitle("Đi qua mua nho");
        resultSearchEntity.setGenre("Van hoc");
        resultSearchEntity.setPublisher("Nha nam");
        resultSearchEntity.setAuthor("Van Cao");
        resultSearchEntity.setDescription("Vi noi nho đong đay");

        inputSearchEntity = new SearchEntity();
        inputSearchEntity.setBook_id(product);
        inputSearchEntity.setTitle("Đi qua mùa nhớ");
        inputSearchEntity.setGenre("Văn học");
        inputSearchEntity.setPublisher("Nhã nam");
        inputSearchEntity.setAuthor("Văn Cao");
        inputSearchEntity.setDescription("Vì nỗi nhớ đong đầy");

    }

    @Test
    public void RemoveAccentsTest_InputSearchProduct_ReturnRemoveAccentProduct(){
        removeAccents = new RemoveAccents();
        SearchEntity resultSearchProduct = removeAccents.removeAccent(inputSearchEntity);

        Assertions.assertNotNull(resultSearchProduct);
        Assertions.assertEquals("Đi qua mua nho",resultSearchProduct.getTitle());
        Assertions.assertEquals(resultSearchEntity.getAuthor(),resultSearchProduct.getAuthor());
    }

}
