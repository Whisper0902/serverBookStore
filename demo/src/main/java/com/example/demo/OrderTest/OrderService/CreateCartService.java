package com.example.demo.OrderTest.OrderService;

import com.example.demo.OrderTest.OrderEntity.Orders;
import com.example.demo.OrderTest.OrderRepository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCartService {

    @Autowired
    private OrderRepository orderRepository;


    public Orders CreateCartService (){

        Orders orders = new Orders();

       return orderRepository.save(orders);
    }

}
