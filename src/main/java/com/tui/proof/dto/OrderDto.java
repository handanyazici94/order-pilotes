package com.tui.proof.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;

@Data
public class OrderDto {

    //@Max(15)
    //@Pattern(regexp = "^[1-9]|1[0-5]\\d*[05]$" , message = "invalid.amount")
    private String pilotes;
    private double orderTotal;

    @Valid
    @NotNull(message = "Please enter client information")
    private AddressDto address;
}
