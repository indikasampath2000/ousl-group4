package ousl.group4.dao.impl;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ousl.group4.dao.ListingDao;
import ousl.group4.model.Listing;

import java.util.List;

@Repository("listingDao")
public class ListingDaoImpl implements ListingDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Listing saveListing(Listing listing) {
        sessionFactory.getCurrentSession().saveOrUpdate(listing);
        return listing;
    }

    @Override
    public Listing getListingById(Long listingId) {
        return (Listing) sessionFactory.getCurrentSession().createCriteria(Listing.class)
                .setFetchMode("item", FetchMode.JOIN)
                .setFetchMode("listingTime", FetchMode.JOIN)
                .setFetchMode("category", FetchMode.JOIN)
                .add(Restrictions.eq("id", listingId))
                .uniqueResult();
    }

    @Override
    public List<Listing> getAllListing() {
        return sessionFactory.getCurrentSession().createCriteria(Listing.class)
                .setFetchMode("item", FetchMode.JOIN)
                .setFetchMode("listingTime", FetchMode.JOIN)
                .setFetchMode("category", FetchMode.JOIN).list();
    }

    @Override
    public List<Listing> getFirstFiveListing() {
        return sessionFactory.getCurrentSession().createCriteria(Listing.class)
                .setFetchMode("item", FetchMode.JOIN)
                .setFetchMode("listingTime", FetchMode.JOIN)
                .setFetchMode("category", FetchMode.JOIN)
                .setMaxResults(5)
                .list();
    }
}
