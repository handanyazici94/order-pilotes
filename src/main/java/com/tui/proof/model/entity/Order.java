package com.tui.proof.model.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;


@Entity
@Table(name="TBL_ORDER")
@Data
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String number;
  private int pilotes;
  private double orderTotal;
  private Instant processTime;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "ADDRESSID")
  private Address deliveryAddress;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "CLIENTID")
  private Client client;

}
