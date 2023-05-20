package com.safemtech.ampqdemo.producer;

import com.safemtech.ampqdemo.constants.AppConstants;
import com.safemtech.ampqdemo.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AMQPProducer {

    private final RabbitTemplate amqpTemplate;

    public AMQPProducer(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void exchange(Orders orders){
        amqpTemplate.convertAndSend(AppConstants.EXCHANGE_NAME, AppConstants.ROUTING_KEY_NAME, orders);
        log.info("SUCCESSFULLY SENT ORDER {} TO QUEUE", orders.getId());

    }
}
