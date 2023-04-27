package com.xbaychallenge.offer.web;

import com.xbaychallenge.listing.web.Listing;
import com.xbaychallenge.user.repository.UserEntity;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offer {

    private UUID id;
    private Listing listing;
    private double price;
    private OfferStatus status;
    private UserEntity buyer;

}
