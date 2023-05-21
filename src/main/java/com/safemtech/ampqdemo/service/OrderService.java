package com.safemtech.ampqdemo.service;

import com.safemtech.ampqdemo.entity.Address;
import com.safemtech.ampqdemo.entity.Orders;
import com.safemtech.ampqdemo.entity.Product;
import com.safemtech.ampqdemo.producer.AMQPProducer;
import com.safemtech.ampqdemo.repository.AddressRepository;
import com.safemtech.ampqdemo.repository.OrderRepository;
import com.safemtech.ampqdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final AMQPProducer amqpProducer;

    public OrderService(OrderRepository orderRepository, AddressRepository addressRepository, ProductRepository productRepository, AMQPProducer amqpProducer) {
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.amqpProducer = amqpProducer;
    }


    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateOrders(@RequestParam String orderId, Map<String, Object> fields){
        Optional<Orders> orderOptional = Optional.of(orderRepository.findById(orderId))
                .orElseThrow(() ->new RuntimeException("No Order found"));
        if(orderOptional.isPresent()){
            Orders order = orderOptional.get();
            fields.forEach((k,v)->{
                Field field = ReflectionUtils.findField(Orders.class, k);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, order, v);
            });
            orderRepository.save(order);
        }

    }

    public Orders createOrder(Orders orders) {
        Address address = orders.getShippingAddress();
        addressRepository.save(address);

        List<Product> products = orders.getProducts();
        productRepository.saveAll(products);
        Orders order = orderRepository.save(orders);
        amqpProducer.exchange(order);
        return order;
    }
}
