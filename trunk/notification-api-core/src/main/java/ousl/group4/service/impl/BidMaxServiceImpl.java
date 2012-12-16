package ousl.group4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ousl.group4.dao.BidMaxDao;
import ousl.group4.model.BidMax;
import ousl.group4.service.BidMaxService;

import java.math.BigDecimal;

@Transactional
@Service("bidMaxService")
public class BidMaxServiceImpl implements BidMaxService{

    @Autowired
    private BidMaxDao bidMaxDao;

    @Override
    public BidMax saveBidMax(BidMax bidMax) {
        return bidMaxDao.saveBidMax(bidMax);
    }

    @Override
    public BigDecimal getMaxBidByListingId(Long listingId) {
        return bidMaxDao.getMaxBidByListingId(listingId);
    }

    @Override
    public BidMax getBidMaxByListingId(Long listingId) {
        return bidMaxDao.getBidMaxByListingId(listingId);
    }
}
