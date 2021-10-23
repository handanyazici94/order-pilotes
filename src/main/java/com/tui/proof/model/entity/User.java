package com.tui.proof.model.entity;

import com.tui.proof.model.enm.Role;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="TBL_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    
}
