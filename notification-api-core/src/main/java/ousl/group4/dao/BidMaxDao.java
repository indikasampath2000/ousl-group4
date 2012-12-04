package ousl.group4.dao;

import ousl.group4.model.BidMax;

import java.math.BigDecimal;

public interface BidMaxDao {

    BidMax saveBidMax(BidMax bidMax);
    BigDecimal getMaxBidByListingId(Long listingId);
}
