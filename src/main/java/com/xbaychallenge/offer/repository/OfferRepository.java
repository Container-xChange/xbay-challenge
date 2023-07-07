package com.xbaychallenge.offer.repository;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferEntity, UUID> {

    Stream<OfferEntity> getAllByListingId(UUID listingId);
}
