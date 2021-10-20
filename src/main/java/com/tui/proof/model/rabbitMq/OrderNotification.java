package com.tui.proof.model.rabbitMq;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;


@Data
public class OrderNotification implements Serializable {

    private String number;
    private int pilotes;
    private double orderTotal;
    private Instant processTime;
    private OrderNotificationStatus status;
    private AddressNotification addressNotification;
    private ClientNotification clientNotification;


}
