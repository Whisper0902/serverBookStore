package com.example.demo.Order.OrderService;

import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteDetailOrderById {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public boolean deleteOrderDetail(Orders order )
    {
        if(order != null)
        {
            orderDetailRepository.deleteByOrderId(order);
            return true;
        }

        return false;
    }
}
