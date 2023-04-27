package com.xbaychallenge.listing.web;

import com.xbaychallenge.user.repository.UserEntity;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Listing {
    private UUID id;
    private String title;
    private String description;
    private String category;
    private UserEntity seller;
    private double price;
}
