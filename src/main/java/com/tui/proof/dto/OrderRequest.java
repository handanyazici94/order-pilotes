package com.tui.proof.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class OrderRequest {

    @Pattern(regexp = "^|5|10|15$" , message = "Enter just 5,10 or 15 number of pilotes")
    @NotEmpty
    private String pilotes;

    @NotNull
    private double orderTotal;

    @Valid
    @NotNull(message = "Please enter client information")
    private AddressRequest address;
}
