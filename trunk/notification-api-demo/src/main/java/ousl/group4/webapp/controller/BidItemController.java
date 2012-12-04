package ousl.group4.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ousl.group4.model.Listing;
import ousl.group4.service.BidMaxService;
import ousl.group4.service.ListingService;

import java.math.BigDecimal;

@Controller
public class BidItemController {

    @Autowired
    private ListingService listingService;
    @Autowired
    private BidMaxService bidMaxService;

    @RequestMapping("/biditem.html")
    public void bidItemHandler() {
    }

    @ModelAttribute("listing")
    public Listing populateListing(@RequestParam("lt") Long listingId){
        Listing listing = listingService.getListingById(listingId);
        BigDecimal maxBid = bidMaxService.getMaxBidByListingId(listingId);
        if(maxBid == null){
            listing.setMaxBid(new BigDecimal(0));
        } else {
            listing.setMaxBid(maxBid);
        }
        listing.setBidCount(listing.getBids().size());

        return listing;
    }

}
