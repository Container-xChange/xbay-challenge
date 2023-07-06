package com.xbaychallenge.listing.web;

import com.xbaychallenge.listing.ListingService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    public Page<Listing> getListings(Pageable pageRequest) {
        return listingService.getListings(pageRequest);
    }

    @GetMapping("/{id}")
    public Listing getListing(@PathVariable  UUID id) {
        return listingService.getListing(id);
    }


    @PostMapping
    public Listing createListing(@RequestBody CreateListingRequest request) {
        return listingService.createListing(request);
    }
}
