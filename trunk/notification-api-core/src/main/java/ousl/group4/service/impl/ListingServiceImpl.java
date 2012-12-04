package ousl.group4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ousl.group4.dao.ListingDao;
import ousl.group4.model.Listing;
import ousl.group4.service.ListingService;

import java.util.List;

@Transactional
@Service("listingService")
public class ListingServiceImpl implements ListingService{

    @Autowired
    private ListingDao listingDao;

    @Override
    public Listing saveListing(Listing listing) {
        return listingDao.saveListing(listing);
    }

    @Override
    public Listing getListingById(Long listingId) {
        return listingDao.getListingById(listingId);
    }

    @Override
    public List<Listing> getAllListing() {
        return listingDao.getAllListing();
    }
}
