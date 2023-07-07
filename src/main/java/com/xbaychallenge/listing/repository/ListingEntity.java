package com.xbaychallenge.listing.repository;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "listings")
public class ListingEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private UUID seller;

    @CreatedDate
    private Instant createdDate;
}
