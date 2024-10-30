package com.example.demo.Order.OrderService;


import com.example.demo.DTO.OrderDto.ProductOfCart;
import com.example.demo.Order.OrderEntity.OrderDetail;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderDetailRepository;
import com.example.demo.Order.OrderRepository.OrderRepository;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductService.UpdateProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaveOrderDetail {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UpdateProductService updateProductService;

    public Boolean saveOrderDetail(List<ProductOfCart> productOfCart, Long id) {
        if (productOfCart == null ) {
            return false;
        }

        try {

            Orders order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with ID " + id + " not found"));

            for(var listProduct : productOfCart)
            {
               OrderDetail orderDetail = new OrderDetail();
               orderDetail.setOrderId(order);
               orderDetail.setUpdateTime(listProduct.getUpdateTime());
               orderDetail.setQuantity(listProduct.getQuantity());
               ProductEntity product = listProduct.getBookId();
               /// Available quantity of product after adding products to the cart
               BigDecimal  availableQuantity = product.getQuantity().subtract(listProduct.getQuantity());
                if (availableQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Out of Stoke");
                    System.out.println("Remaining quantity of "+listProduct.getBookId().getTitle()+ " " + product.getQuantity());
                    return false;
                }
               Map<String,Object> mQuantity = new HashMap<>();
               mQuantity.put("quantity",availableQuantity);
                ProductEntity productEntity= updateProductService.updateProduct(mQuantity,listProduct.getBookId().getId());
                System.out.println(productEntity);



               orderDetail.setCreated(listProduct.getCreated());
               orderDetail.setBookId(listProduct.getBookId());
               orderDetail.setTotal(listProduct.getTotal());
                orderDetailRepository.save(orderDetail);
            }
            return true;
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(SaveCartService.class);
            logger.error("Error saving product of cart: {}", e.getMessage(), e);
            return false;
        }
    }
}
