package com.example.demo.Order.OrderRepository;

import com.example.demo.Order.OrderEntity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {


}
