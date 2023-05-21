package com.safemtech.ampqdemo.config;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import static com.safemtech.ampqdemo.constants.AppConstants.EXCHANGE_NAME;
import static com.safemtech.ampqdemo.constants.AppConstants.QUEUE_NAME;
import static com.safemtech.ampqdemo.constants.AppConstants.ROUTING_KEY_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MessageConfigTest {

    private final MessageConfig config = new MessageConfig();
    @Test
    void queue() {
        Queue queue = config.queue();
        assertEquals(queue.getName(), QUEUE_NAME);
    }

    @Test
    void exchange() {
        TopicExchange exchange = config.exchange();
        assertEquals(exchange.getName(), EXCHANGE_NAME);
    }

    @Test
    void binding() {
        Binding binding = config.binding(new Queue(QUEUE_NAME), new TopicExchange(EXCHANGE_NAME));
        assertEquals(binding.getRoutingKey(), ROUTING_KEY_NAME);
        assertEquals(binding.getDestination(), QUEUE_NAME);
        assertEquals(binding.getExchange(), EXCHANGE_NAME);
    }

    @Test
    void converter() {
        MessageConverter converter = config.converter();
        assertEquals(converter.getClass(), Jackson2JsonMessageConverter.class);
    }

    @Test
    void template() {
        AmqpTemplate template = config.template(mock(ConnectionFactory.class));
        assertEquals(template.getClass(), RabbitTemplate.class);
    }
}