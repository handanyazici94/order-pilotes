package com.tui.proof.model.rabbitMq;

import lombok.Data;

@Data
public class AddressNotification {

    private String street;
    private String postcode;
    private String city;
    private String country;
}
