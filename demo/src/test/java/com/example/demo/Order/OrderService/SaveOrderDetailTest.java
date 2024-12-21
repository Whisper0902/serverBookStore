package com.example.demo.Order.OrderService;

import com.example.demo.DTO.OrderDto.ProductOfCart;
import com.example.demo.Order.OrderRepository.OrderDetailRepository;
import com.example.demo.Order.OrderRepository.OrderRepository;
import com.example.demo.Product.ProductEntity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SaveOrderDetailTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CheckQuantityOfProduct checkQuantityOfProduct;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @InjectMocks
    private SaveOrderDetail saveOrderDetailClass;

    private ProductOfCart productOfCart;

    private ProductEntity product;

    private List<ProductOfCart> productOfCartList;

    private List<ProductOfCart> listProductEmpty;

    @BeforeEach
    public void setUp(){

        product = new ProductEntity();
        product.setId(1L);
        product.setTitle("Di qua mua nang");

        productOfCart = new ProductOfCart();
        productOfCart.setBookId(product);
        productOfCart.setQuantity(BigDecimal.valueOf(2));

        productOfCartList = new ArrayList<>();
        productOfCartList.add(productOfCart);

        listProductEmpty = new ArrayList<>();

    }

    @Test
    public void saveOrderDetail_WhenListProductIsEmpty_ReturnRuntimeException(){

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            saveOrderDetailClass.saveOrderDetail(listProductEmpty, 2L);
        });

        assertEquals("List product to save cart is empty",exception.getMessage());

    }


}
