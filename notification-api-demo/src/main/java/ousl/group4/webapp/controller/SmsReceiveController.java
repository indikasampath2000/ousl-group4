package ousl.group4.webapp.controller;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ousl.group4.exception.NotificationAPIException;
import ousl.group4.model.Bid;
import ousl.group4.model.BidMax;
import ousl.group4.model.Listing;
import ousl.group4.model.User;
import ousl.group4.service.BidMaxService;
import ousl.group4.service.BidService;
import ousl.group4.service.ListingService;
import ousl.group4.service.UserService;
import ousl.group4.sms.model.SmsKeyBox;
import ousl.group4.sms.service.SmsSender;
import ousl.group4.webapp.util.PhoneNumberDecoder;
import ousl.group4.webapp.util.SmsMessageDecoder;

@Controller
public class SmsReceiveController {

	@Autowired
	private UserService userService;
	@Autowired
	private ListingService listingService;
	@Autowired
	private BidMaxService bidMaxService;
    @Autowired
    private BidService bidService;
	@Autowired
	private SmsSender smsSender;

	@RequestMapping(value = "/sms-receiver.html", method = RequestMethod.GET)
	public void receiveSms(@RequestParam("phone") String phoneNumber, @RequestParam("text") String message) {
		// get phone number and message from URL parameters
		String mobileNumber = PhoneNumberDecoder.decodePhoneNumber(phoneNumber);
		String sms = SmsMessageDecoder.decodeMessage(message);
		StringTokenizer tokenizer = new StringTokenizer(sms);
		String keyword = new String();
		while (tokenizer.hasMoreElements()) {
			keyword = (String) tokenizer.nextElement();
			if (keyword.equalsIgnoreCase("list")) {
				break;
			}
			if (keyword.equalsIgnoreCase("bid")) {
				break;
			}
			if (keyword.equalsIgnoreCase("act")) {
				break;
			}
		}
		User user = userService.getUserByMobilePhoneNumber(mobileNumber);
		// check user is registered and account enabled
		if (user != null) {
			if (user.isEnabled()) {
				// sms list
				String searchBidItems = "";
				if (keyword.equalsIgnoreCase("list")) {
					List<Listing> listingList = listingService.getFirstFiveListing();
					for (Listing listing : listingList) {
						BigDecimal currentBid = bidMaxService.getMaxBidByListingId(listing.getId());
                        if(currentBid == null){
                            currentBid = new BigDecimal(0);
                        }
						searchBidItems = searchBidItems + listing.getId() + " " + listing.getItem().getSmsSearchName()
								+ " " + currentBid + "\n";
					}
					searchBidItems = searchBidItems + "<group4> <bid> <id> <your bid>";
					// generate result to send via sms and send
					Map<String, Object> smsMap = new HashMap<String, Object>();
					smsMap.put(SmsKeyBox.SENDER, "0720260442");
					smsMap.put(SmsKeyBox.RECIPIENTS, new String[] { mobileNumber });
					smsMap.put(SmsKeyBox.SMS_BODY, searchBidItems);
					try {
						smsSender.send(smsMap);
					} catch (NotificationAPIException e) {
						e.printStackTrace();
					}
				}
				// sms bidding
				if (keyword.equalsIgnoreCase("bid")) {
					String bidSms = sms;
					String listingId = null;
					String bidPrice = null;
					StringTokenizer stringTokenizer = new StringTokenizer(bidSms);
					while (stringTokenizer.hasMoreElements()) {
						if (stringTokenizer.nextElement().toString().equalsIgnoreCase("bid")) {
							listingId = (String) stringTokenizer.nextElement();
							bidPrice = (String) stringTokenizer.nextElement();
							break;
						}
					}
					Listing listing = listingService.getListingById(Long.parseLong(listingId));
					// check listing is not null
					if (listing != null) {
						// save bid
						Bid bid = new Bid();
						bid.setCreatedOn(new Date());
						bid.setEditedOn(new Date());
						bid.setListing(listing);
						bid.setStatus(true);
						bid.setUser(user);
						bid.setValue(new BigDecimal(Double.parseDouble(bidPrice)));
                        bidService.saveBid(bid);
						// save max bid
						BigDecimal maxBid = bidMaxService.getMaxBidByListingId(listing.getId());
                        if(maxBid == null){
                            maxBid = new BigDecimal(0);
                        }
						if (maxBid.doubleValue() < Double.parseDouble(bidPrice)) {
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
							bidMax.setMaxBid(new BigDecimal(Double.parseDouble(bidPrice)));
							bidMax.setUser(user);
							bidMax.setEditedOn(new Date());
							bidMaxService.saveBidMax(bidMax);
							// generate sms
							Map<String, Object> smsMap = new HashMap<String, Object>();
							smsMap.put(SmsKeyBox.SENDER, "0720260442");
							smsMap.put(SmsKeyBox.RECIPIENTS, new String[] { mobileNumber });
							smsMap.put(SmsKeyBox.SMS_BODY, "Congratulation ! You are the Max bidder.");
							try {
								smsSender.send(smsMap);
							} catch (NotificationAPIException e) {
								e.printStackTrace();
							}
						} else {
							// generate sms
							Map<String, Object> smsMap = new HashMap<String, Object>();
							smsMap.put(SmsKeyBox.SENDER, "0720260442");
							smsMap.put(SmsKeyBox.RECIPIENTS, new String[] { mobileNumber });
							smsMap.put(SmsKeyBox.SMS_BODY, "Sorry ! You are out bid.");
							try {
								smsSender.send(smsMap);
							} catch (NotificationAPIException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				// activate user account
				if (keyword.equalsIgnoreCase("act")) {
					String activate = sms;
					String activationCode = null;
					StringTokenizer stringTokenizer = new StringTokenizer(activate);
					while (stringTokenizer.hasMoreElements()) {
						if (stringTokenizer.nextElement().toString().equalsIgnoreCase("act")) {
							activationCode = (String) stringTokenizer.nextElement();
							break;
						}
					}
					if (user.getVerificationCode() == Integer.parseInt(activationCode)) {
						user.setEnabled(true);
						userService.saveUser(user);
						// generate sms
						Map<String, Object> smsMap = new HashMap<String, Object>();
						smsMap.put(SmsKeyBox.SENDER, "0720260442");
						smsMap.put(SmsKeyBox.RECIPIENTS, new String[] { mobileNumber });
						smsMap.put(SmsKeyBox.SMS_BODY,
								"Your account has been successfully activated. Thank you for registered in CeyBid.com");
						try {
							smsSender.send(smsMap);
						} catch (NotificationAPIException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
