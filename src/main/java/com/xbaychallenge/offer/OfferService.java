package com.xbaychallenge.offer;

import com.xbaychallenge.listing.ListingService;
import com.xbaychallenge.listing.web.Listing;
import com.xbaychallenge.notification.NotificationEvent;
import com.xbaychallenge.offer.repository.OfferEntity;
import com.xbaychallenge.offer.repository.OfferEntityBuilder;
import com.xbaychallenge.offer.repository.OfferEntityStatus;
import com.xbaychallenge.offer.repository.OfferRepository;
import com.xbaychallenge.offer.web.CreateOfferRequest;
import com.xbaychallenge.offer.web.Offer;
import com.xbaychallenge.offer.web.OfferStatus;
import com.xbaychallenge.user.UserService;
import com.xbaychallenge.user.repository.UserEntity;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository repository;
    private final ListingService listingService;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public Offer createOffer(CreateOfferRequest offerRequest) {
        OfferEntity offerEntity = buildEntity(offerRequest);
        OfferEntity savedOffer = repository.save(offerEntity);
        publishNotification(savedOffer);
        return buildOffer(savedOffer);
    }

    private void publishNotification(OfferEntity offer) {
        UserEntity buyer = userService.getUser(offer.getBuyerId());
        UserEntity seller = listingService.getListing(offer.getListingId())
                                          .getSeller();
        eventPublisher.publishEvent(NotificationEvent.builder()
                                                     .email(buyer.getEmail())
                                                     .subject("New Offer")
                                                     .message("You have created a new offer: " + offer.getId())
                                                     .build());
        eventPublisher.publishEvent(NotificationEvent.builder()
                                                     .email(seller.getEmail())
                                                     .subject("New Offer")
                                                     .message("You have received a new offer: " + offer.getId())
                                                     .build());
    }

    public Offer getOffer(UUID offerId) {
        OfferEntity offerEntity = repository.getReferenceById(offerId);
        return buildOffer(offerEntity);
    }

    @Transactional
    public void rejectOffer(UUID offerId) {
        OfferEntity offerEntity = repository.getReferenceById(offerId);
        offerEntity.setStatus(OfferEntityStatus.REJECTED);

        publishRejectNotification(offerEntity);
    }

    private void publishRejectNotification(OfferEntity offer) {
        UserEntity buyer = userService.getUser(offer.getBuyerId());
        eventPublisher.publishEvent(NotificationEvent.builder()
                                                     .email(buyer.getEmail())
                                                     .subject("Offer rejected")
                                                     .message("Your offer has been rejected: " + offer.getId())
                                                     .build());
    }

    @Transactional
    public void acceptOffer(UUID offerId) {
        OfferEntity offerEntity = repository.getReferenceById(offerId);
        offerEntity.setStatus(OfferEntityStatus.ACCEPTED);

        publishAcceptNotification(offerEntity);
    }

    private void publishAcceptNotification(OfferEntity offer) {
        UserEntity buyer = userService.getUser(offer.getBuyerId());
        eventPublisher.publishEvent(NotificationEvent.builder()
                                                     .email(buyer.getEmail())
                                                     .subject("Offer accepted")
                                                     .message("Your offer has been accepted: " + offer.getId())
                                                     .build());
    }

    private Offer buildOffer(OfferEntity offerEntity) {
        UUID listingId = offerEntity.getListingId();
        Listing listing = listingService.getListing(listingId);
        OfferStatus offerStatus = OfferStatusMapper.map(offerEntity.getStatus());
        UserEntity buyer = userService.getUser(offerEntity.getBuyerId());
        return Offer.builder()
                    .id(offerEntity.getId())
                    .listing(listing)
                    .price(offerEntity.getPrice())
                    .status(offerStatus)
                    .buyer(buyer)
                    .build();
    }

    private OfferEntity buildEntity(CreateOfferRequest offerRequest) {
        return OfferEntityBuilder.builder()
                                 .id(UUID.randomUUID())
                                 .buyerId(offerRequest.getBuyerId())
                                 .listingId(offerRequest.getListingId())
                                 .build();
    }
}
