package com.example.demo.Order.OrderService;

import com.example.demo.Order.OrderEntity.OrderDetail;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderDetailRepository;
import com.example.demo.Order.OrderRepository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class SaveOrderDetail {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CheckQuantityOfProduct checkQuantityOfProduct;


    @Transactional(rollbackFor = Exception.class)
    public List<OrderDetail> saveOrderDetail(List<OrderDetail> listProductOfCart) {
        if (listProductOfCart.isEmpty()) {
            throw new RuntimeException("List product to save cart is empty");
        }
        Orders order = orderRepository.findById(listProductOfCart.get(0).getOrderId().getId())
                .orElseThrow(() -> new RuntimeException("Order with ID not found"));

        for (var listProduct : listProductOfCart) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order);
            orderDetail.setUpdateTime(listProduct.getUpdateTime());
            orderDetail.setQuantity(listProduct.getQuantity());
            orderDetail.setCreated(listProduct.getCreated());
            orderDetail.setBookId(listProduct.getBookId());
            orderDetail.setTotal(listProduct.getTotal());

            boolean success = checkQuantityOfProduct.purchaseProduct(listProduct.getBookId().getId(), listProduct.getQuantity());

            if (success) {
                OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail);
                if (saveOrderDetail.getOrderId() == null) {
                    throw new RuntimeException("Can not save orders detail");
                }
            }
        }
        return listProductOfCart;
    }
}

