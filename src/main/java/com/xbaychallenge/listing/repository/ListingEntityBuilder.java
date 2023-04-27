package com.xbaychallenge.listing.repository;


import java.util.UUID;

public class ListingEntityBuilder {

    private String title;
    private String description;
    private String category;
    private double price;
    private UUID seller;

    public static ListingEntityBuilder builder() {
        return new ListingEntityBuilder();
    }

    public ListingEntityBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ListingEntityBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ListingEntityBuilder price(double price) {
        this.price = price;
        return this;
    }

    public ListingEntityBuilder seller(UUID seller) {
        this.seller = seller;
        return this;
    }

    public ListingEntityBuilder category(String category) {
        this.category = category;
        return this;
    }

    public ListingEntity build() {
        ListingEntity listingEntity = new ListingEntity();
        listingEntity.setTitle(title);
        listingEntity.setDescription(description);
        listingEntity.setPrice(price);
        listingEntity.setSeller(seller);
        listingEntity.setCategory(category);
        return listingEntity;
    }
}
