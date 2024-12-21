package com.example.demo.Order.OrderService;

import com.example.demo.DTO.OrderDto.ProductOfCart;
import com.example.demo.Order.OrderEntity.OrderDetail;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderDetailRepository;
import com.example.demo.Order.OrderRepository.OrderRepository;
import com.example.demo.Product.ProductService.UpdateProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public List<ProductOfCart> saveOrderDetail(List<ProductOfCart> listProductOfCart, Long orderId) {
        if (listProductOfCart.isEmpty()) {
            throw new RuntimeException("List product to save cart is empty");
        }
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with ID " + orderId + " not found"));

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
                orderDetailRepository.save(orderDetail);
                System.out.println("Purchase successful");

            } else {
                throw new RuntimeException("Not enough stock for product " + listProduct.getBookId().getTitle());
            }
        }
        return listProductOfCart;

    }
}

