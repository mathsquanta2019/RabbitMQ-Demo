package com.safemtech.ampqdemo.listner;

import com.safemtech.ampqdemo.constants.AppConstants;
import com.safemtech.ampqdemo.entity.Orders;
import com.safemtech.ampqdemo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MessageListener {

    private final OrderService orderService;

    public MessageListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = AppConstants.QUEUE_NAME)
    public void consume(Orders orders){
        Map<String, Object> fieldsToUpdateOnConsumption = new HashMap<>();
        fieldsToUpdateOnConsumption.put("status", "PROCESSED");
        orderService.updateOrders(orders.getId(), fieldsToUpdateOnConsumption);
        log.info("SUCCESSFULLY PROCESSED ORDER {}!", orders.getId());
    }
}
