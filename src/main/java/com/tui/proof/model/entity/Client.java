package com.tui.proof.model.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="TBL_CLIENT")
@Data
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String telephone;

  @Column(unique = true)
  @Email
  @NotNull
  private String email;

  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name="CLIENTID")
  private Set<Order> orders;




}
