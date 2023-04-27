package com.xbaychallenge.listing.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingsRepository extends JpaRepository<ListingEntity, UUID> {
}
