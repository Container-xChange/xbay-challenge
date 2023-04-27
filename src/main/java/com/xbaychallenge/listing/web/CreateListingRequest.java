package com.xbaychallenge.listing.web;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateListingRequest {

    private String title;
    private String description;
    private String category;
    private UUID sellerId;
    private double price;

}
