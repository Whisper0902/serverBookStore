package com.example.demo.Order.OrderController;
import com.example.demo.DTO.OrderDto.CartDto;
import com.example.demo.DTO.OrderDto.ProductOfCart;
import com.example.demo.Order.OrderEntity.OrderDetail;
import com.example.demo.Order.OrderEntity.Orders;
import com.example.demo.Order.OrderRepository.OrderRepository;
import com.example.demo.Order.OrderService.CreateCartService;
import com.example.demo.Order.OrderService.SaveCartService;
import com.example.demo.Order.OrderService.SaveOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/createCart")
    public ResponseEntity<Orders> createCartController()
    {
        Orders createCart = createCartService.createCart();
        if (createCart.getId() == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(createCart, HttpStatus.ACCEPTED);
    }

    @PostMapping("/saveCart")
    public ResponseEntity<Orders> saveCartController(@RequestBody CartDto orders ) {
      Orders orderSave = saveCartService.saveCart(orders) ;

      if( orderSave.getId() == null )
      {
          return new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE);
      }
        return new ResponseEntity<>(orderSave,HttpStatus.CREATED);
    }

    @PostMapping("/saveOrderDetail")
    public ResponseEntity<List<OrderDetail>> saveOrderDetailController(@RequestBody List<OrderDetail> listProduct )
    {
        List<OrderDetail> listProductSave = saveOrderDetail.saveOrderDetail(listProduct);
        if( listProductSave.isEmpty() )
        {
            return new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(listProductSave,HttpStatus.CREATED);

    }
}
