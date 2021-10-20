package com.tui.proof.model.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="TBL_ADDRESS")
@Data
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String street;

  @NotBlank
  private String postcode;

  @NotBlank
  private String city;

  @NotBlank
  private String country;

}
