package com.xbaychallenge.offer.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferEntity, UUID> {
}
