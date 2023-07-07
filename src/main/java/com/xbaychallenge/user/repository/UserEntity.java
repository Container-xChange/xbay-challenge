package com.xbaychallenge.user.repository;

import jakarta.persistence.*;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

}
