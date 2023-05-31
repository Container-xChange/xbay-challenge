package com.xbaychallenge.listing.repository;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    private String category;

    private double price;

    private UUID seller;

    @CreatedDate
    private Instant createdDate;
}
