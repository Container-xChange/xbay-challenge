package com.xbaychallenge.listing.repository;


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
}
