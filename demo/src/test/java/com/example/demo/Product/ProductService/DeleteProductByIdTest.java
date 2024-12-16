package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteProductByIdTest {


    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private DeleteProductByIdService deleteProductByIdService;

    Long productId;
    ProductEntity product;
    String productStr;

    @BeforeEach
    public void setUp() {
        productId = 1L;
        productStr = "str";
        product = new ProductEntity();
        product.setId(1L);
        product.setTitle("Dám Nghĩ Lớn");
        product.setGenre("Văn học");
    }


    @Test
    public void CheckIdIsTypeLong_ReturnException() {
        DeleteProductByIdService service = new DeleteProductByIdService();
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
        {
            service.deleteProductById(null);
        });
        assertEquals("value is not type Long",exception.getMessage());
    }

    @Test
    public void DeleteProductById_InputLongProductId_ReturnProductEntity() {
        when(productHomepage.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productHomepage).deleteById(productId);
        ProductEntity resultProduct = deleteProductByIdService.deleteProductById(productId);
        assertEquals(product, resultProduct);



    }

}
