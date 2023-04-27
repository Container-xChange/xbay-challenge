package com.xbaychallenge.offer;

import com.xbaychallenge.offer.repository.OfferEntityStatus;
import com.xbaychallenge.offer.web.OfferStatus;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum OfferStatusMapper {

    PENDING(OfferStatus.PENDING, OfferEntityStatus.PENDING),
    ACCEPTED(OfferStatus.ACCEPTED, OfferEntityStatus.ACCEPTED),
    REJECTED(OfferStatus.REJECTED, OfferEntityStatus.REJECTED);

    private final OfferStatus offerStatus;
    private final OfferEntityStatus offerEntityStatus;


    public static OfferStatus map(OfferEntityStatus offerEntityStatus) {
        for (OfferStatusMapper offerStatusMapper : OfferStatusMapper.values()) {
            if (offerStatusMapper.offerEntityStatus.equals(offerEntityStatus)) {
                return offerStatusMapper.offerStatus;
            }
        }
        throw new IllegalArgumentException("No mapping found for offerEntityStatus: " + offerEntityStatus);
    }

}
