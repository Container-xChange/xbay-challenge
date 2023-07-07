package com.xbaychallenge.listing.repository;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingsRepository extends JpaRepository<ListingEntity, UUID> {

    Stream<ListingEntity> getAllByCategory(String category);
}
