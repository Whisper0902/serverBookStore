package com.example.demo.Order.OrderController;
import com.example.demo.DTO.OrderDto.CartDto;
import com.example.demo.DTO.OrderDto.ProductOfCart;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderService.CreateCartService;
import com.example.demo.Order.OrderService.SaveCartService;
import com.example.demo.Order.OrderService.SaveOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private SaveCartService saveCartService;

    @Autowired
    private CreateCartService createCartService;

    @Autowired
    private SaveOrderDetail saveOrderDetail;

    @PostMapping("/createCart")
    public ResponseEntity<Orders> createCart()
    {
        Orders createCart = createCartService.CreateCartService();
        return new ResponseEntity<>(createCart, HttpStatus.CREATED);
    }

    @PostMapping("/saveCart")
    public ResponseEntity<Void> saveCart(@RequestBody CartDto orders, @RequestParam Long id ) {
      boolean isSaveCart = saveCartService.saveCart(orders , id) ;


      if( isSaveCart == true )
      {
          return new ResponseEntity<>( HttpStatus.CREATED);
      }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/saveOrderDetail")
    public ResponseEntity<Void> saveOrderDetail(@RequestBody List<ProductOfCart> listProduct ,@RequestParam Long id)
    {
        boolean isSaveProduct = saveOrderDetail.saveOrderDetail(listProduct,id);
        if( isSaveProduct == true )
        {
            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
