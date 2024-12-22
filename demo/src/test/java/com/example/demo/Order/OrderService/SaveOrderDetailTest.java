package com.example.demo.Order.OrderService;

import com.example.demo.DTO.OrderDto.ProductOfCart;
import com.example.demo.Order.OrderEntity.OrderDetail;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderDetailRepository;
import com.example.demo.Order.OrderRepository.OrderRepository;
import com.example.demo.Product.ProductEntity.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    private OrderDetail productOfCart;
    private OrderDetail productOfCartOne;
    private ProductEntity product;
    private ProductEntity productOne;
    private Orders orders;
    private Orders orderOne;
    private OrderDetail orderDetailNull;
    private List<OrderDetail> productOfCartList;
    private List<OrderDetail> listProductEmpty;

    @BeforeEach
    public void setUp() {

        product = new ProductEntity();
        product.setId(1L);
        product.setTitle("Di qua mua nang");

        orders = new Orders();
        orders.setId(1L);

        orderOne = new Orders();
        orderOne.setId(2L);

        productOne = new ProductEntity();
        productOne.setId(2L);
        productOne.setTitle("Di qua mua mua");

        productOfCart = new OrderDetail();
        productOfCart.setOrderId(orders);
        productOfCart.setBookId(product);
        productOfCart.setQuantity(BigDecimal.valueOf(2));

        productOfCartOne = new OrderDetail();
        productOfCartOne.setOrderId(orderOne);
        productOfCartOne.setBookId(productOne);
        productOfCartOne.setQuantity(BigDecimal.valueOf(4));

        productOfCartList = new ArrayList<>();
        productOfCartList.add(productOfCart);
        productOfCartList.add(productOfCartOne);

        listProductEmpty = new ArrayList<>();

        orderDetailNull = new OrderDetail();


    }

    @Test
    public void saveOrderDetail_WhenListProductIsEmpty_ReturnRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            saveOrderDetailClass.saveOrderDetail(listProductEmpty);
        });

        assertEquals("List product to save cart is empty", exception.getMessage());
    }

    @Test
    public void saveOrderDetail_WhenListProductOfCartSaved_ReturnListProductOfCart() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        when(checkQuantityOfProduct.purchaseProduct(any(Long.class), any(BigDecimal.class))).thenReturn(true)
                .thenReturn(true);
        when(orderDetailRepository.save(any(OrderDetail.class))).thenReturn(productOfCart)
                .thenReturn(productOfCartOne);

        List<OrderDetail> listProductResult = saveOrderDetailClass.saveOrderDetail(productOfCartList);

        Assertions.assertNotNull(listProductResult);
        Assertions.assertFalse(listProductResult.isEmpty());

    }

    @Test
    public void saveOrderDetail_WhenNotSaveOrderDetail_ReturnRuntimeException(){
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        when(checkQuantityOfProduct.purchaseProduct(any(Long.class), any(BigDecimal.class))).thenReturn(true)
                .thenReturn(true);
        when(orderDetailRepository.save(any(OrderDetail.class))).thenReturn(productOfCart)
                .thenReturn(orderDetailNull);

        RuntimeException exception = assertThrows(RuntimeException.class,() ->{
            saveOrderDetailClass.saveOrderDetail(productOfCartList);
        });

        Assertions.assertEquals("Can not save orders detail",exception.getMessage());

    }


}
