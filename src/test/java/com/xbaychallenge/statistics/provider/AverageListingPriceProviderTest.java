package com.xbaychallenge.statistics.provider;

import com.xbaychallenge.listing.ListingService;
import com.xbaychallenge.listing.web.Listing;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AverageListingPriceProviderTest {

    private static final String SOME_CATEGORY = "someCategory";

    @Mock
    private ListingService listingService;
    @InjectMocks
    private AverageListingPriceProvider averageListingPriceProvider;

    @Test
    void thatNameIsAverageListingPrice() {
        // given & when
        String name = averageListingPriceProvider.getName();

        // then
        assertThat(name).isEqualTo("averageListingPrice");
    }

    @Test
    void thatAverageListingPriceIsCorrect() {
        // given
        Listing listingA = Listing.builder().price(11.11).build();
        Listing listingB = Listing.builder().price(22.22).build();
        Listing listingC = Listing.builder().price(55.55).build();

        when(listingService.getListingsForCategory(SOME_CATEGORY))
                .thenReturn(Stream.of(listingA, listingB, listingC));

        // when
        Double result = averageListingPriceProvider.getValue(SOME_CATEGORY);

        // then
        assertThat(result).isEqualTo(29.63, Offset.offset(0.01));

    }
}