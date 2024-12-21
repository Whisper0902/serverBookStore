package com.example.demo.Order.OrderService;

import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCartService {

    @Autowired
    private OrderRepository orderRepository;


    public Orders createCart (){

        Orders orders = new Orders();

       return orderRepository.save(orders);
    }

}
