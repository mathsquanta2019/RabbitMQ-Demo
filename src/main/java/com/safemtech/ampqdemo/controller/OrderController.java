package com.safemtech.ampqdemo.controller;

import com.safemtech.ampqdemo.entity.Orders;
import com.safemtech.ampqdemo.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/createOrder")
    public Orders createOrder(@RequestBody Orders orders){
        return orderService.createOrder(orders);
    }
}
