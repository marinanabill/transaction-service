package com.wallet.transaction_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "users") 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

}
