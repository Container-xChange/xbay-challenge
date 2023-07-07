package com.xbaychallenge.offer.repository;

import jakarta.persistence.*;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "offers")
public class OfferEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID listingId;

    @Column(nullable = false)
    private UUID buyerId;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private OfferEntityStatus status = OfferEntityStatus.PENDING;

}
