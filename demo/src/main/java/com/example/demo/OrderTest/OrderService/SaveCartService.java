package com.example.demo.OrderTest.OrderService;

import com.example.demo.DTO.OrderDto.CartDto;
import com.example.demo.OrderTest.OrderEntity.Orders;
import com.example.demo.OrderTest.OrderRepository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SaveCartService {


    @Autowired
    private OrderRepository orderRepository;


    public boolean saveCart(CartDto orders , Long id) {
        if (orders == null ) {
            throw new NoSuchElementException("No order is found ");
        }

        try {
           Orders order = orderRepository.findById(id) .orElseThrow(() -> new EntityNotFoundException("Order with ID " + id + " not found"));

            order.setCreated(orders.getCreatedTime());
            order.setPaymentMethod(orders.getPaymentMethod());
            order.setTotal(orders.getTotalCart());
            order.setUpdateTime(orders.getUpdateTime());
            order.setCustomerId(orders.getCustomerAccount());
            orderRepository.save(order);
            return true;

        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(SaveCartService.class);
            logger.error("Error saving cart: {}", e.getMessage(), e);
            throw new NoSuchElementException("No order found with null payment method");
        }
    }
}
