package com.xbaychallenge;

import com.xbaychallenge.listing.ListingService;
import com.xbaychallenge.listing.web.CreateListingRequest;
import com.xbaychallenge.listing.web.Listing;
import com.xbaychallenge.offer.OfferService;
import com.xbaychallenge.offer.web.CreateOfferRequest;
import com.xbaychallenge.offer.web.Offer;
import com.xbaychallenge.offer.web.OfferStatus;
import com.xbaychallenge.user.UserService;
import com.xbaychallenge.user.repository.UserEntity;
import com.xbaychallenge.user.web.CreateUserRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TheTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ListingService listingService;

    @Autowired
    private OfferService offerService;

    @Test
    public void happyPath() {
        CreateUserRequest createBob = CreateUserRequest.builder()
                                                       .firstName("Bob")
                                                       .lastName("Buyer")
                                                       .email("bob@thebuyer.com")
                                                       .build();
        UserEntity bob = userService.createUser(createBob);

        CreateUserRequest createAlice = CreateUserRequest.builder()
                                                         .firstName("Alice")
                                                         .lastName("Seller")
                                                         .email("alice@theselles.com")
                                                         .build();
        UserEntity alice = userService.createUser(createAlice);

        CreateListingRequest listingRequest = CreateListingRequest.builder()
                                                                  .title("A thing")
                                                                  .description("A thing that is for sale")
                                                                  .category("Stuff")
                                                                  .sellerId(alice.getId())
                                                                  .price(10.0)
                                                                  .build();
        Listing listing = listingService.createListing(listingRequest);

        CreateOfferRequest offerRequest = CreateOfferRequest.builder()
                                                            .listingId(listing.getId())
                                                            .buyerId(bob.getId())
                                                            .price(7.5)
                                                            .build();
        Offer offer = offerService.createOffer(offerRequest);

        offerService.acceptOffer(offer.getId());

        Offer acceptedOffer = offerService.getOffer(offer.getId());
        assertEquals(OfferStatus.ACCEPTED, acceptedOffer.getStatus());
    }
}
