package ousl.group4.dao.impl;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ousl.group4.dao.BidMaxDao;
import ousl.group4.model.BidMax;

import java.math.BigDecimal;

@Repository("bidMaxDao")
public class BidMaxDaoImpl implements BidMaxDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BidMax saveBidMax(BidMax bidMax) {
        sessionFactory.getCurrentSession().saveOrUpdate(bidMax);
        return bidMax;
    }

    @Override
    public BigDecimal getMaxBidByListingId(Long listingId) {

        return (BigDecimal) sessionFactory.getCurrentSession().createCriteria(BidMax.class)
                .setFetchMode("listing", FetchMode.JOIN)
                .setProjection(Projections.max("maxBid"))
                .add(Restrictions.eq("listing.id", listingId))
                .uniqueResult();

    }

    @Override
    public BidMax getBidMaxByListingId(Long listingId) {
        return (BidMax) sessionFactory.getCurrentSession().createCriteria(BidMax.class)
                .setFetchMode("listing", FetchMode.JOIN)
                .add(Restrictions.eq("listing.id", listingId))
                .uniqueResult();
    }
}
