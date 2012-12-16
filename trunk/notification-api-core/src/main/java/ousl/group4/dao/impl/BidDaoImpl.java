package ousl.group4.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ousl.group4.dao.BidDao;
import ousl.group4.model.Bid;

@Repository("bidDao")
public class BidDaoImpl implements BidDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Bid saveBid(Bid bid) {
        sessionFactory.getCurrentSession().save(bid);
        sessionFactory.getCurrentSession().flush();
        return bid;
    }
}
