package com.example.demo.Order.OrderService;

import com.example.demo.DTO.OrderDto.CartDto;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveCartTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private SaveCartService saveCartService;

    private CartDto cartDto;
    private Orders orders;
    private Orders resultOrder;
    private Orders orderNull;
    private CartDto cartDtoNull;

    @BeforeEach
    public void setUp(){
        cartDtoNull = new CartDto();
        orderNull = new Orders();

        cartDto = new CartDto();
        cartDto.setId(1L);

        resultOrder = new Orders();
        resultOrder.setId(1L);

        orders = new Orders();
        orders.setId(1L);


    }

    @Test
    public void saveCart_WhenOrdersInputIsNull_ReturnNoSuchElementException(){
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,()->{
            saveCartService.saveCart(cartDtoNull);
        });
        Assertions.assertEquals("No order is empty",exception.getMessage());
    }

    @Test
    public void saveCart_WhenNotFoundOrder_ReturnRuntimeException(){
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderNull));
        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            saveCartService.saveCart(cartDto);
        });

        Assertions.assertEquals("Not found cart by Id",exception.getMessage());
    }

    @Test
    public void saveCart_WhenSaveOrderIsTrue_ReturnOrder(){
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        when(orderRepository.save(any(Orders.class))).thenReturn(resultOrder);

        Orders resultOrders = saveCartService.saveCart(cartDto);

        Assertions.assertNotNull(resultOrders);
        Assertions.assertEquals(1L,resultOrders.getId());
    }

}
