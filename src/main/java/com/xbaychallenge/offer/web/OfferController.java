package com.xbaychallenge.offer.web;

import com.xbaychallenge.offer.OfferService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity createOffer(CreateOfferRequest offerRequest) {
        Offer offer = offerService.createOffer(offerRequest);
        return ResponseEntity.ok(offer);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity getOffer(UUID offerId) {
        Offer offer = offerService.getOffer(offerId);
        return ResponseEntity.ok(offer);
    }

    @PutMapping("/reject")
    public ResponseEntity rejectOffer(UUID offerId) {
        offerService.rejectOffer(offerId);
        return ResponseEntity.noContent()
                             .build();
    }

    @PutMapping("/accept")
    public ResponseEntity acceptOffer(UUID offerId) {
        offerService.acceptOffer(offerId);
        return ResponseEntity.noContent()
                             .build();
    }
}
