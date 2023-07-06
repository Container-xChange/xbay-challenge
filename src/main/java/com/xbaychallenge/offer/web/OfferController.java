package com.xbaychallenge.offer.web;

import com.xbaychallenge.offer.OfferService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity createOffer(@RequestBody CreateOfferRequest offerRequest) {
        Offer offer = offerService.createOffer(offerRequest);
        return ResponseEntity.ok(offer);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity getOffer(@PathVariable UUID offerId) {
        Offer offer = offerService.getOffer(offerId);
        return ResponseEntity.ok(offer);
    }

    @PutMapping("/{offerId}/reject")
    public ResponseEntity rejectOffer(@PathVariable UUID offerId) {
        offerService.rejectOffer(offerId);
        return ResponseEntity.noContent()
                             .build();
    }

    @PutMapping("/{offerId}/accept")
    public ResponseEntity acceptOffer(@PathVariable UUID offerId) {
        offerService.acceptOffer(offerId);
        return ResponseEntity.noContent()
                             .build();
    }
}
