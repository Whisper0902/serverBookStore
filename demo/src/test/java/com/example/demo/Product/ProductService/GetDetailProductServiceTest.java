package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetDetailProductServiceTest {

    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private GetDetailProductService getDetailProductService;

    Long productId;
    ProductEntity product;

    @BeforeEach
    public void setUp(){
        productId = 1L;
        product = new ProductEntity();
        product.setId(1L);
        product.setTitle("Lan cuoi ta nam tay nhau");
        product.setGenre("Ky su");

    }

    @Test
    public void getDetailProduct_CheckIdIsNotNull()
    {
        GetDetailProductService getDetail = new GetDetailProductService();
        RuntimeException exception = assertThrows(RuntimeException.class,()-> {
            getDetail.getDetailProduct(null);
        }) ;
        assertEquals("Value id is null",exception.getMessage());

    }

    @Test
    public void GetDetailProduct_ReturnProductEntity(){
        when(productHomepage.findById(productId)).thenReturn(Optional.of(product));
        ProductEntity resultProduct = getDetailProductService.getDetailProduct(productId);

        assertNotEquals(null,resultProduct);
        assertEquals(1L,resultProduct.getId());
        assertEquals(product,resultProduct);
    }
}
