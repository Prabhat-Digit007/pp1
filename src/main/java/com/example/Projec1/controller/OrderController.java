package com.example.Projec1.controller;

import com.example.Projec1.entity.Order;
import com.example.Projec1.entity.OrderRequest;
import com.example.Projec1.services.IOrderService;
import com.example.Projec1.services.OrderService;
import com.example.Projec1.util.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestHeader("Authorization") String token,@RequestBody OrderRequest orderRequest) {
        Long id = AuthMiddleware.getIdFromToken(token);
        Order order = orderService.placeOrder(orderRequest,id);
        return ResponseEntity.ok(order);
    }
}
