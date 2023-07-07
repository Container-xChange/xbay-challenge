package com.xbaychallenge.statistics.provider;

import com.xbaychallenge.listing.ListingService;
import com.xbaychallenge.listing.web.Listing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class AverageListingPriceProvider implements CategoryStatisticsProvider<Double> {

    private final ListingService listingService;

    @Override
    public String getName() {
        return "averageListingPrice";
    }

    @Transactional
    @Override
    public Double getValue(String category) {
        return listingService.getListingsForCategory(category)
                .mapToDouble(Listing::getPrice)
                .average().orElse(Double.NaN);
    }
}
