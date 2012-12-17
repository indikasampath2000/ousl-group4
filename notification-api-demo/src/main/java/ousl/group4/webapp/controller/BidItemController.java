package ousl.group4.webapp.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ousl.group4.email.model.MailKeyBox;
import ousl.group4.email.model.MailSendType;
import ousl.group4.email.service.MailSender;
import ousl.group4.exception.NotificationAPIException;
import ousl.group4.model.Bid;
import ousl.group4.model.BidMax;
import ousl.group4.model.Listing;
import ousl.group4.model.User;
import ousl.group4.service.BidMaxService;
import ousl.group4.service.BidService;
import ousl.group4.service.ListingService;
import ousl.group4.sms.model.SmsKeyBox;

@Controller
public class BidItemController {

	@Autowired
	private ListingService listingService;
	@Autowired
	private BidMaxService bidMaxService;
    @Autowired
    private BidService bidService;
    @Autowired
    private MailSender mailSender;

	@RequestMapping("/biditem.html")
	public void bidItemHandler() {
	}

	@ModelAttribute("listing")
	public Listing populateListing(@RequestParam("lt") Long listingId, ModelMap modelMap) {
		Listing listing = listingService.getListingById(listingId);
		BigDecimal maxBid = bidMaxService.getMaxBidByListingId(listingId);
		if (maxBid == null) {
			listing.setMaxBid(new BigDecimal(0));
		} else {
			listing.setMaxBid(maxBid);
		}
		listing.setBidCount(listing.getBids().size());
		Date endDate = listing.getEndTime();
		Date now = new Date();
		Long timeDifference = Math.abs(endDate.getTime() - now.getTime());

        String hours = String.format("%d", TimeUnit.MILLISECONDS.toHours(timeDifference));

        String minutes = String.format("%d",
                TimeUnit.MILLISECONDS.toMinutes(timeDifference) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDifference)));

        String seconds = String.format("%d",
				TimeUnit.MILLISECONDS.toSeconds(timeDifference) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeDifference)));

        modelMap.addAttribute("hours", hours);
        modelMap.addAttribute("minutes", minutes);
        modelMap.addAttribute("seconds", seconds);

        return listing;

	}

    @RequestMapping(value = "/place-bid.html", method = RequestMethod.POST)
    public String processBid(@RequestParam("lt") Long listingId, @RequestParam("bidAmount")String bidAmount, ModelMap modelMap){
        Listing listing = listingService.getListingById(listingId);
        String result = null;
        modelMap.addAttribute("listingId", listingId);
        // check listing is not null
        if (listing != null) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String name = user.getUsername();

            // save bid
            Bid bid = new Bid();
            bid.setCreatedOn(new Date());
            bid.setEditedOn(new Date());
            bid.setListing(listing);
            bid.setStatus(true);
            bid.setUser(user);
            bid.setValue(new BigDecimal(Double.parseDouble(bidAmount)));
            bidService.saveBid(bid);
            // save max bid
            BigDecimal maxBid = bidMaxService.getMaxBidByListingId(listing.getId());
            if(maxBid == null){
                maxBid = new BigDecimal(0);
            }
            if (maxBid.doubleValue() < Double.parseDouble(bidAmount)) {
                BidMax bidMax = null;
                try{
                    bidMax = bidMaxService.getBidMaxByListingId(listing.getId());
                } catch (Exception e){
                    e.printStackTrace();
                }

                if (bidMax == null) {
                    bidMax = new BidMax();
                }
                bidMax.setListing(listing);
                bidMax.setMaxBid(new BigDecimal(Double.parseDouble(bidAmount)));
                bidMax.setUser(user);
                bidMax.setEditedOn(new Date());
                bidMaxService.saveBidMax(bidMax);
                // generate email

                //generate email including activation number
                Map<String,Object> mailMap =new HashMap<String, Object>();
                mailMap.put(MailKeyBox.SUBJECT,"Congratulation ! Your the max bidder");
                mailMap.put(MailKeyBox.SENDER,"ouslgroup4@gmail.com");
                mailMap.put(MailKeyBox.RECIPIENTS, new String[][]{{user.getEmail(), MailSendType.SEND_TO}});
                mailMap.put("fullName", user.getFullName());
                mailMap.put("username",user.getUsername());
                mailMap.put("title","Your the max bidder");
                mailMap.put("name",user.getFirstName());
                mailMap.put("bid", bidAmount);
                mailMap.put("itemName", listing.getItem().getName());
                mailMap.put("itemId", listing.getItem().getId());
                mailMap.put("endTime", listing.getEndTime());
                mailMap.put(MailKeyBox.INLINE_IMAGES, new String[][]{{"/tmp/logo_x60.png", "logo_x60"}});

                try {
                    mailSender.send(mailMap,"/mailTemplates/bidPlaced.vm");
                } catch (NotificationAPIException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = "max-bid";
            } else {
                //generate email including activation number
                Map<String,Object> mailMap =new HashMap<String, Object>();
                mailMap.put(MailKeyBox.SUBJECT,"Sorry ! Your are out bid.");
                mailMap.put(MailKeyBox.SENDER,"ouslgroup4@gmail.com");
                mailMap.put(MailKeyBox.RECIPIENTS, new String[][]{{user.getEmail(), MailSendType.SEND_TO}});
                mailMap.put("fullName", user.getFullName());
                mailMap.put("username",user.getUsername());
                mailMap.put("title","Your out bid");
                mailMap.put("name",user.getFirstName());
                mailMap.put("bid", bidAmount);
                mailMap.put("itemName", listing.getItem().getName());
                mailMap.put("itemId", listing.getItem().getId());
                mailMap.put("endTime", listing.getEndTime());
                mailMap.put(MailKeyBox.INLINE_IMAGES, new String[][]{{"/tmp/logo_x60.png", "logo_x60"}});

                try {
                    mailSender.send(mailMap,"/mailTemplates/bidPlaced.vm");
                } catch (NotificationAPIException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = "out-bid";
            }
        }
         return result;
    }
}
