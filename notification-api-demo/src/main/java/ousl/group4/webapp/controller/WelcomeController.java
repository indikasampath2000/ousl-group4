package ousl.group4.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ousl.group4.model.BidMax;
import ousl.group4.model.Listing;
import ousl.group4.service.BidMaxService;
import ousl.group4.service.ListingService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class WelcomeController {

    @Autowired
    private ListingService listingService;
    @Autowired
    private BidMaxService bidMaxService;

    @RequestMapping("/welcome.html")
    public void welcomeHandler() {

    }

    /**
     * load listing to display in carousel
     *
     * @return
     */
    @ModelAttribute("listings")
    public List<Listing> populateListing(){
        List<Listing> listings = new ArrayList<Listing>();

        for(Object o : listingService.getAllListing()){
            Listing listing = (Listing) o;
            BigDecimal maxBid = bidMaxService.getMaxBidByListingId(listing.getId());
            if(maxBid == null){
                listing.setMaxBid(new BigDecimal(0));
            } else {
                listing.setMaxBid(maxBid);
            }

            Date endDate = listing.getEndTime();
            Date now = new Date();

            Long timeDifference = Math.abs(endDate.getTime() - now.getTime());

            String remainingTime = String.format("%d:%d:%d", TimeUnit.MILLISECONDS.toDays(timeDifference),
                    TimeUnit.MILLISECONDS.toHours(timeDifference) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(timeDifference)),
                    TimeUnit.MILLISECONDS.toMinutes(timeDifference) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDifference)));

            listing.setRemainingTime(remainingTime);

            listings.add(listing);
        }
        return listings;
    }
}
