package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateProductServiceTest {

    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private UpdateProductService updateProductService;

    private ProductEntity product;
    private ProductEntity productUpdate;



    private ProductEntity nullProduct;

    @BeforeEach
    public void setUp() {
        product = new ProductEntity();
        product.setId(10L);
        product.setTitle("MÃI ĐỪNG XA TÔI");
        product.setGenre("Truyện dài");
        product.setAuthor("KAZUO ISHIGURO");
        product.setDescription("Mãi Đừng Xa Tôi là câu chuyện mang tính khoa học");
        product.setPrice(BigDecimal.valueOf(150));
        product.setPublisher("VĂN HỌC");

        nullProduct = new ProductEntity();

        productUpdate = new ProductEntity();
        productUpdate.setId(10L);
        productUpdate.setTitle("Toi da xa noi nay");
        productUpdate.setGenre("Truyen ngan");
        productUpdate.setAuthor("Nha Nam");
        productUpdate.setDescription("Toi da xa noi nay là câu chuyện mang tính khoa học");
        productUpdate.setPrice(BigDecimal.valueOf(200));
        productUpdate.setPublisher("Tho Ca");



    }

    @Test
    public void UpdateProductService_TestExceptionWhenProductInputNull_ReturnRuntimeException(){

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            updateProductService.updateProduct(nullProduct);
        });

        assertEquals("Value of product is null",exception.getMessage());
    }

    @Test
    public void UpdateProductService_TestExceptionWhenNotFindProduct_ReturnRuntimeException(){

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            updateProductService.updateProduct(product);

        });
        assertEquals("Product with ID " + product.getId() +" not found",exception.getMessage());

    }

    @Test
    public void UpdateProductService_TestUpdateProduct_ReturnProductUpdate(){
        when(productHomepage.findById(productUpdate.getId())).thenReturn(Optional.ofNullable(product));
        when(productHomepage.save(any(ProductEntity.class))).thenReturn(productUpdate);

        ProductEntity resultProductUpdate = updateProductService.updateProduct(productUpdate);

        assertEquals(10L,resultProductUpdate.getId());
        assertNotNull(resultProductUpdate);
        assertEquals("Toi da xa noi nay",resultProductUpdate.getTitle());
        assertEquals("Truyen ngan",resultProductUpdate.getGenre());
        assertEquals(BigDecimal.valueOf(200),resultProductUpdate.getPrice());
    }




}
