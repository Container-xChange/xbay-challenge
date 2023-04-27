package com.xbaychallenge.offer.repository;

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
@Table(name = "offers")
public class OfferEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID listingId;

    private UUID buyerId;

    private double price;

    private OfferEntityStatus status = OfferEntityStatus.PENDING;

}
