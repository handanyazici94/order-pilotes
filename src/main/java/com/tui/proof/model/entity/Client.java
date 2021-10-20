package com.tui.proof.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="TBL_CLIENT")
@Data
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String telephone;

  @Column(unique = true)
  @Email
  private String email;

  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name="CLIENTID")
  private Set<Order> orders;




}
