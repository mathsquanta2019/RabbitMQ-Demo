package com.safemtech.ampqdemo.controller;

import com.safemtech.ampqdemo.entity.Orders;
import com.safemtech.ampqdemo.producer.AMQPProducer;
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

    private final AMQPProducer amqpProducer;

    public OrderController(OrderService orderService, AMQPProducer amqpProducer) {
        this.orderService = orderService;
        this.amqpProducer = amqpProducer;
    }

    @GetMapping("/orders")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/createOrder")
    public Orders createOrder(@RequestBody Orders orders){
        Orders order = orderService.createOrder(orders);
        amqpProducer.exchange(order);
        return order;
    }
}
