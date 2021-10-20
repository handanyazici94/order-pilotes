package com.tui.proof.dto;

import lombok.Data;

import javax.validation.Valid;
import java.time.Instant;

@Data
public class OrderResponse {

    private Long id;
    private int pilotes;
    private double orderTotal;
    private String number;
    private Instant processTime;
    private AddressDto address;
    private ClientDto client;
}
