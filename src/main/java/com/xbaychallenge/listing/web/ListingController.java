package com.xbaychallenge.listing.web;

import com.xbaychallenge.listing.ListingService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    public Page<Listing> getListings(PageRequest pageRequest) {
        return listingService.getListings(pageRequest);
    }

    @GetMapping("/{id}")
    public Listing getListing(UUID id) {
        return listingService.getListing(id);
    }


    @PostMapping
    public Listing createListing(CreateListingRequest request) {
        return listingService.createListing(request);
    }
}
