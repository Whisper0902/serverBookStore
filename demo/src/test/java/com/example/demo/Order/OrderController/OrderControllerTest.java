package com.example.demo.Order.OrderController;

import com.beust.ah.A;
import com.example.demo.DTO.OrderDto.CartDto;
import com.example.demo.DTO.OrderDto.ProductOfCart;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderService.CreateCartService;
import com.example.demo.Order.OrderService.SaveCartService;
import com.example.demo.Order.OrderService.SaveOrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private CreateCartService createCartService;
    @Mock
    private SaveCartService saveCartService;
    @Mock
    private SaveOrderDetail saveOrderDetail;

    @InjectMocks
    private OrderController orderController;

    private Orders orders;

    private Orders orderNull;

    private CartDto cartNull;

    private CartDto cartDto;

    private ProductOfCart productOfCart;
    private List<ProductOfCart> productOfCartListNull;
    private List<ProductOfCart> productOfCartList;

    @BeforeEach
    public void setUp() {
        orders = new Orders();
        orders.setId(1L);

        orderNull = new Orders();

        cartNull = new CartDto();
        cartDto = new CartDto();
        cartDto.setId(1L);

        productOfCart = new ProductOfCart();
        productOfCart.setQuantity(BigDecimal.valueOf(2));

        productOfCartList = new ArrayList<>();
        productOfCartList.add(productOfCart);

        productOfCartListNull = new ArrayList<>();

    }

    @Test
    public void createCartController_WhenCartCreateIsNull_ReturnHttpStatusNotAcceptable() {

        when(createCartService.createCart()).thenReturn(orderNull);

        ResponseEntity<Orders> responseEntity = orderController.createCartController();

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
    }

    @Test
    public void createCartController_WhenCreateCartIsAccepted_ReturnHttpStatusAccept(){
        when(createCartService.createCart()).thenReturn(orders);

        ResponseEntity<Orders> responseEntity = orderController.createCartController();

        Assertions.assertEquals(HttpStatus.ACCEPTED,responseEntity.getStatusCode());
        Assertions.assertEquals(1L,responseEntity.getBody().getId());
        Assertions.assertNotNull(responseEntity);
    }

    @Test
    public void saveCartController_WhenSaveCartNotAccepted_ReturnHttpStatusNotAccepted(){
        when(saveCartService.saveCart(cartNull)).thenReturn(orderNull);

        ResponseEntity<Orders> responseEntity = orderController.saveCartController(cartNull);

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE,responseEntity.getStatusCode());
    }

    @Test
    public void saveCartController_WhenSaveCartIsAccept_ReturnHttpStatusAccept(){
        when(saveCartService.saveCart(cartDto)).thenReturn(orders);

        ResponseEntity<Orders> responseEntity = orderController.saveCartController(cartDto);

        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(1L,responseEntity.getBody().getId());
    }

    @Test
    public void saveCartController_WhenSaveListDetailProductNotAccepted_ReturnHttpStatusNotAcceptable(){
        when(saveOrderDetail.saveOrderDetail(productOfCartListNull,1L)).thenReturn(productOfCartListNull);

        ResponseEntity<List<ProductOfCart>> responseEntity = orderController.saveOrderDetailController(productOfCartListNull,1L);

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE,responseEntity.getStatusCode());
    }

    @Test
    public void saveCartController_WhenSaveProductAccepted_ReturnHttpStatusCreate(){
        when(saveOrderDetail.saveOrderDetail(productOfCartList,1L)).thenReturn(productOfCartList);

        ResponseEntity<List<ProductOfCart>> responseEntity = orderController.saveOrderDetailController(productOfCartList,1L);

        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity);


    }


}
