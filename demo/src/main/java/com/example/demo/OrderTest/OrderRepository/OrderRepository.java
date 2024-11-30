package com.example.demo.OrderTest.OrderRepository;


import com.example.demo.OrderTest.OrderEntity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long>
{

}