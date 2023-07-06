package com.xbaychallenge.listing.web;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateListingRequest {

    private String title;
    private String description;
    private String category;
    private UUID sellerId;
    private double price;

}
