package com.example.demo.Order.OrderRepository;


import com.example.demo.Order.OrderEntity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long>
{

}