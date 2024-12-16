package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductByIdTest {

    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private GetProductById getProductByIdClass;

    private Long productId;
    private Long getProductIdTwo;
    ProductEntity product;
    @BeforeEach
    public void setUp(){
        productId = 1L;
        product = new ProductEntity();

        getProductIdTwo = 2L;

        product.setId(getProductIdTwo);
        product.setTitle("Di qua mua nho");
        product.setGenre("Van hoc");

    }

    @Test
    public void GetProductByIdService_TestThrowException_ReturnRunTimeException(){
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,() -> {
            getProductByIdClass.getProductById(productId);
        });
        Assertions.assertEquals("Product with ID " + productId + " not found", exception.getMessage());
    }

    @Test
    public void GetProductByIdService_TestGetProductById_ReturnProduct(){
        when(productHomepage.findById(getProductIdTwo)).thenReturn(Optional.of(product));
        ProductEntity resultProduct = getProductByIdClass.getProductById(getProductIdTwo);

        Assertions.assertNotNull(resultProduct);
        Assertions.assertEquals(product, resultProduct);
        Assertions.assertTrue(resultProduct.getId().equals(2L));
    }
}
