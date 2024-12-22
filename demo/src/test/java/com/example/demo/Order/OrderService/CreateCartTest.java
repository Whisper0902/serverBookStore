package com.example.demo.Order.OrderService;

import com.example.demo.Order.OrderEntity.OrderDetail;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class CreateCartTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private CreateCartService createCartService;

    private Orders orders;
    private Orders orderNull;
    private Orders orderResult;

    @BeforeEach
    private void setUp() {
        orders = new Orders();

        orderResult = new Orders();
        orderResult.setId(1L);

        orderNull = new Orders();

    }

    @Test
    public void createCart_WhenCreateProduct_ReturnOrder(){
        when(orderRepository.save(any(Orders.class))).thenReturn(orderResult);
        Orders resultOrder = createCartService.createCart();

        Assertions.assertNotNull(resultOrder);
        Assertions.assertEquals(1L,resultOrder.getId());

    }

    @Test
    public void createCart_WhenNotSaveProduct_ReturnRuntimeException(){
        when(orderRepository.save(any(Orders.class))).thenReturn(orderNull);

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            createCartService.createCart();
        });

        Assertions.assertEquals("Can not save order",exception.getMessage());
    }

}
