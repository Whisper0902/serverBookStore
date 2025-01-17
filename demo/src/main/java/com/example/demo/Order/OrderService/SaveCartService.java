package com.example.demo.Order.OrderService;

import com.example.demo.DTO.OrderDto.CartDto;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SaveCartService {


    @Autowired
    private OrderRepository orderRepository;

    public Orders saveCart(CartDto orders) {
        if (orders.getId() == null) {
            throw new NoSuchElementException("No order is empty");
        }

        Orders order = orderRepository.findById(orders.getId()).orElseThrow(() -> new EntityNotFoundException("Order with ID " + orders.getId() + " not found"));
        if(order.getId() == null)
        {
            throw new RuntimeException("Not found cart by Id");
        }
        order.setCustomerId(orders.getCustomerAccount());
        order.setPaymentMethod(orders.getPaymentMethod());
        order.setTotal(orders.getTotalCart());
        order.setCustomerId(orders.getCustomerId());
        order.setCreated(orders.getCreatedTime());
        order.setUpdateTime(orders.getUpdateTime());

        Orders resultOrder = orderRepository.save(order);

        if (resultOrder.getId() == null) {
            throw new RuntimeException("Not save product");
        }
        return resultOrder;
    }
}
