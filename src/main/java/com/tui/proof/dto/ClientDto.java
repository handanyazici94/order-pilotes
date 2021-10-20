package com.tui.proof.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ClientDto {

    @NotEmpty(message = "Please provide a firstName")
    private String firstName;
    @NotEmpty(message = "Please provide a lastName")
    private String lastName;
    @NotEmpty(message = "Please provide a telephone")
    private String telephone;
    @Email
    @NotEmpty(message = "Please provide an email")
    private String email;
}
