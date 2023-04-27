package com.xbaychallenge.offer.repository;

import java.util.UUID;


public class OfferEntityBuilder {

    private UUID id;
    private UUID buyerId;
    private UUID listingId;

    public static OfferEntityBuilder builder() {
        return new OfferEntityBuilder();
    }


    public OfferEntityBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public OfferEntityBuilder buyerId(UUID buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public OfferEntityBuilder listingId(UUID listingId) {
        this.listingId = listingId;
        return this;
    }

    public OfferEntity build() {
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setId(id);
        offerEntity.setBuyerId(buyerId);
        offerEntity.setListingId(listingId);
        return offerEntity;
    }

}
