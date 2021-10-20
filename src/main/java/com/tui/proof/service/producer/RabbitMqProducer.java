package com.tui.proof.service.producer;

import com.tui.proof.model.rabbitMq.OrderNotification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${tui.rabbitmq.takeOrder.exchange}")
    private String exchange;

    @Value("${tui.rabbitmq.takeOrder.routingKey}")
    private String routingKey;

    public void sendOrder(OrderNotification orderNotification) {
        rabbitTemplate.convertAndSend(exchange, routingKey, orderNotification);
        System.out.println("Send msg = " + orderNotification);

    }

    public void updateOrder(OrderNotification orderNotification) {
        rabbitTemplate.convertAndSend(exchange, routingKey, orderNotification);
        System.out.println("Send msg = " + orderNotification);

    }
}
