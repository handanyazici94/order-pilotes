package com.tui.proof.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddressDto {

    @NotEmpty(message = "Please provide an street")
    private String street;

    @NotEmpty(message = "Please provide an city")
    private String city;

    @NotEmpty(message = "Please provide an country")
    private String country;

    @NotEmpty(message = "Please provide an postCode")
    private String postcode;
}
