package com.tui.proof.service.listener;

import com.tui.proof.model.rabbitMq.OrderNotification;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqListener {

   // @RabbitListener(queues = "tui.takeOrder.queue")
    public void handleAddingOrderMessage (OrderNotification orderNotification) {
        System.out.println("Rabbit Listener");
    }
}
