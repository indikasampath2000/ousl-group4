package ousl.group4.service;

import ousl.group4.model.Listing;

import java.util.List;

public interface ListingService {

    Listing saveListing(Listing listing);
    Listing getListingById(Long listingId);
    List<Listing> getAllListing();
    List<Listing> getFirstFiveListing();
}
