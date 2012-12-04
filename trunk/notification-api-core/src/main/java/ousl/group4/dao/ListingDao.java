package ousl.group4.dao;

import ousl.group4.model.Listing;

import java.util.List;

public interface ListingDao {

    Listing saveListing(Listing listing);
    Listing getListingById(Long listingId);
    List<Listing> getAllListing();
}
