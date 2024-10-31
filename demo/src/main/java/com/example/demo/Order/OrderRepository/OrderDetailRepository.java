package com.example.demo.Order.OrderRepository;

import com.example.demo.Order.OrderEntity.OrderDetail;
import com.example.demo.Order.OrderEntity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {


    @Modifying
    @Transactional
    void deleteByOrderId(Orders orderId);
}
