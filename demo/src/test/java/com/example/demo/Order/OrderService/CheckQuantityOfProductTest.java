package com.example.demo.Order.OrderService;

import com.example.demo.Product.ProductRepository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckQuantityOfProductTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CheckQuantityOfProduct checkQuantityOfProduct;

    private Long productId;
    private BigDecimal purchaseQuantity;

    private int updateRowNegative;

    private int updateRowPositive;

    @BeforeEach
    private void setUp() {
        productId = 1L;
        purchaseQuantity = BigDecimal.valueOf(2);
        updateRowNegative = 1;
        updateRowPositive = -1;

    }

    @Test
    public void purchaseProduct_WhenPurchaseIsNotAllowed_ReturnRuntimeException() {
        when(productRepository.updateProductQuantity(productId,purchaseQuantity)).thenReturn(updateRowPositive);

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            checkQuantityOfProduct.purchaseProduct(productId,purchaseQuantity);
        });

        Assertions.assertEquals("There isn't enough stock left",exception.getMessage());
    }
    @Test
    public void purchaseProduct_WhenPurchaseIsAllow_ReturnTrue(){
        when(productRepository.updateProductQuantity(productId,purchaseQuantity)).thenReturn(updateRowNegative);

        boolean resultTrue = checkQuantityOfProduct.purchaseProduct(productId,purchaseQuantity);

        Assertions.assertTrue(resultTrue);

    }
}
