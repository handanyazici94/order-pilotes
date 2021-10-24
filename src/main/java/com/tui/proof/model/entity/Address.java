package com.tui.proof.model.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="TBL_ADDRESS")
@Data
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String street;

  @NotNull
  private String postcode;

  @NotNull
  private String city;

  @NotNull
  private String country;

}
