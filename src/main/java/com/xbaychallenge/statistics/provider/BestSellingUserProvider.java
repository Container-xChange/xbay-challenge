package com.xbaychallenge.statistics.provider;

import com.xbaychallenge.listing.ListingService;
import com.xbaychallenge.listing.web.Listing;
import com.xbaychallenge.offer.OfferService;
import com.xbaychallenge.offer.web.Offer;
import com.xbaychallenge.offer.web.OfferStatus;
import com.xbaychallenge.user.repository.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class BestSellingUserProvider implements CategoryStatisticsProvider<String> {

    private final ListingService listingService;
    private final OfferService offerService;

    @Override
    public String getName() {
        return "bestSellingUser";
    }

    @Transactional
    @Override
    public String getValue(String category) {
        return listingService.getListingsForCategory(category)
                .collect(Collectors.groupingBy(Listing::getSeller, Collectors.toList()))
                .entrySet()
                .stream()
                .map(entry -> Map.entry(entry.getKey(), getVolumeForUser(entry.getKey().getId(), entry.getValue())))
                .sorted((a,b) -> Double.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .map(UserEntity::getEmail)
                .findFirst().orElse("N/A");
    }

    private double getVolumeForUser(UUID userId, Collection<Listing> listings) {
        return listings.stream()
                .flatMap(listing -> offerService.getOffersForListing(listing.getId()))
                .filter(offer -> offer.getStatus() == OfferStatus.ACCEPTED)
                .mapToDouble(Offer::getPrice)
                .average().orElse(0.0);
    }
}
