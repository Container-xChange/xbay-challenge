package com.xbaychallenge.user.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String firstname;
    private String surname;
    private String email;

}
