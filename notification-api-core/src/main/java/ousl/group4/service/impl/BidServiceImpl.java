package ousl.group4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ousl.group4.dao.BidDao;
import ousl.group4.model.Bid;
import ousl.group4.service.BidService;

@Transactional
@Service("bidService")
public class BidServiceImpl implements BidService{

    @Autowired
    private BidDao bidDao;

    @Override
    public Bid saveBid(Bid bid) {
        return bidDao.saveBid(bid);
    }
}
