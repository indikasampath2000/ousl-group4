package ousl.group4.service;

import ousl.group4.model.BidMax;

import java.math.BigDecimal;

public interface BidMaxService {

    BidMax saveBidMax(BidMax bidMax);
    BigDecimal getMaxBidByListingId(Long listingId);
}
