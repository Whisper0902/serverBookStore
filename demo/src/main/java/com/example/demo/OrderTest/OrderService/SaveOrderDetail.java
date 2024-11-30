package com.example.demo.OrderTest.OrderService;

import com.example.demo.DTO.OrderDto.ProductOfCart;
import com.example.demo.OrderTest.OrderEntity.OrderDetail;
import com.example.demo.OrderTest.OrderEntity.Orders;
import com.example.demo.OrderTest.OrderRepository.OrderDetailRepository;
import com.example.demo.OrderTest.OrderRepository.OrderRepository;
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
    private UpdateProductService updateProductService;

    @Autowired
    private CheckQuantityOfProduct checkQuantityOfProduct;
    @Autowired
    private DeleteDetailOrderById deleteDetailOrderById;

    @Transactional(rollbackFor = Exception.class)
    public Boolean saveOrderDetail(List<ProductOfCart> productOfCart, Long id) {
        if (productOfCart == null ) {
            return false;
        }

        try {

            Orders order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order with ID " + id + " not found"));

            for(var listProduct : productOfCart)
            {
               OrderDetail orderDetail = new OrderDetail();
               orderDetail.setOrderId(order);
               orderDetail.setUpdateTime(listProduct.getUpdateTime());
               orderDetail.setQuantity(listProduct.getQuantity());
                orderDetail.setCreated(listProduct.getCreated());
                orderDetail.setBookId(listProduct.getBookId());
                orderDetail.setTotal(listProduct.getTotal());


                boolean success = checkQuantityOfProduct.purchaseProduct(listProduct.getBookId().getId(),listProduct.getQuantity());

                if (success) {
                    orderDetailRepository.save(orderDetail);
                    System.out.println("Purchase successful");

                } else {
//                    deleteDetailOrderById.deleteOrderDetail(order);
                    System.out.println("Not enough stock available");
                    System.out.println(" Remaining Available of " +listProduct.getBookId().getTitle()+ ": " +listProduct.getBookId().getQuantity());
                    throw new RuntimeException("Not enough stock for product " + listProduct.getBookId().getTitle()); // Ném ngoại lệ

                }

            }
            return true;
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(SaveOrderDetail.class);
            logger.error("Error saving product of cart: {}", e.getMessage(), e);
            throw e;
        }
    }
}

/// Available quantity of product after adding products to the cart
//                ProductEntity product = listProduct.getBookId();
//               BigDecimal  availableQuantity = product.getQuantity().subtract(listProduct.getQuantity());
//                if (availableQuantity.compareTo(BigDecimal.ZERO) <= 0) {
//                    System.out.println("Out of Stoke");
//                    System.out.println("Remaining quantity of "+listProduct.getBookId().getTitle()+ " " + product.getQuantity());
//                    return false;
//                }
//               Map<String,Object> mQuantity = new HashMap<>();
//               mQuantity.put("quantity",availableQuantity);
//                ProductEntity productEntity= updateProductService.updateProduct(mQuantity,listProduct.getBookId().getId());