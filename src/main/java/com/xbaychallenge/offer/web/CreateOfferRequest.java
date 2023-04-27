package com.xbaychallenge.offer.web;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOfferRequest {

    private UUID listingId;
    private UUID buyerId;
    private double price;
}
