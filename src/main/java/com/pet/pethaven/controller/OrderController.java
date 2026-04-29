package com.pet.pethaven.controller;

import com.pet.pethaven.dto.OrderDTO;
import com.pet.pethaven.model.Order;
import com.pet.pethaven.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO orderDTO){
        Order order = orderService.saveOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @GetMapping("/getOrdersByEmail")
    public ResponseEntity<List<Order>> getOrderByEmail(@RequestParam("email") String email){
        List<Order> orders = orderService.getOrdersByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
