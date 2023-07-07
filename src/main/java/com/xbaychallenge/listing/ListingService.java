package com.xbaychallenge.listing;

import com.xbaychallenge.listing.repository.ListingEntity;
import com.xbaychallenge.listing.repository.ListingEntityBuilder;
import com.xbaychallenge.listing.repository.ListingsRepository;
import com.xbaychallenge.listing.web.CreateListingRequest;
import com.xbaychallenge.listing.web.Listing;
import com.xbaychallenge.notification.NotificationEvent;
import com.xbaychallenge.user.UserService;
import com.xbaychallenge.user.repository.UserEntity;
import java.util.UUID;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingsRepository repository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public Page<Listing> getListings(Pageable pageRequest) {
        return repository.findAll(pageRequest)
                         .map(this::buildDTO);
    }

    public Stream<Listing> getListingsForCategory(String category) {
        return repository.getAllByCategory(category)
            .map(this::buildDTO);
    }

    public Listing getListing(UUID id) {
        return repository.findById(id)
                         .map(this::buildDTO)
                         .orElseThrow(NullPointerException::new);
    }

    public Listing createListing(CreateListingRequest request) {
        ListingEntity entity = buildEntity(request);
        ListingEntity savedListing = repository.save(entity);
        publishNotificationEvent(savedListing);
        return buildDTO(savedListing);
    }

    private void publishNotificationEvent(ListingEntity listing) {
        UUID seller = listing.getSeller();
        UserEntity user = userService.getUser(seller);
        String email = user.getEmail();
        String fullName = user.getFirstname() + " " + user.getSurname();
        String message = String.format("Dear %s, you have created a new listing", fullName);
        eventPublisher.publishEvent(NotificationEvent.builder()
                                                     .email(email)
                                                     .subject("New Listing")
                                                     .message(message)
                                                     .build());
    }

    private ListingEntity buildEntity(CreateListingRequest request) {
        return ListingEntityBuilder.builder()
                                   .title(request.getTitle())
                                   .description(request.getDescription())
                                   .category(request.getCategory())
                                   .seller(request.getSellerId())
                                   .price(request.getPrice())
                                   .build();
    }

    private Listing buildDTO(ListingEntity entity) {
        UUID sellerId = entity.getSeller();
        UserEntity user = userService.getUser(sellerId);
        return Listing.builder()
                      .id(entity.getId())
                      .title(entity.getTitle())
                      .description(entity.getDescription())
                      .category(entity.getCategory())
                      .price(entity.getPrice())
                      .seller(user)
                      .build();
    }
}
