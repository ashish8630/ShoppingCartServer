package com.example.shoping.controller;

import java.util.List;
import com.example.shoping.entities.Orders;
import com.example.shoping.services.OrderService;
import com.example.shoping.utils.OrderBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderBody orderBody){
        Orders orders=this.orderService.createOrder(orderBody);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }
    @PutMapping("cancel/{orderId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<Orders> cancelOrder(@PathVariable Integer orderId){
        Orders orders=this.orderService.cancelOrder(orderId);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/notDelivered/{userId}")
    public ResponseEntity<List<Orders>> getAllNotDeliveredOrders(@PathVariable String userId){
        List<Orders> orders=this.orderService.getActiveAndUndeliveredOrdersByUser(userId);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    //Future Works



    @PutMapping("delivery/{orderId}")
    public ResponseEntity<Orders> deliverOrder(@PathVariable Integer orderId){
        Orders orders=this.orderService.seDeliveryStatus(orderId);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    @GetMapping("/delivered/{userId}")
    public ResponseEntity<List<Orders>> getAlldeliveredOrders(@PathVariable String userId){
        List<Orders> orders=this.orderService.getActiveAndDeliveredOrdersByUser(userId);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Orders>> getAllActiveOrders(){
        List<Orders> orders=this.orderService.getAllActiveOrders();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }


}
