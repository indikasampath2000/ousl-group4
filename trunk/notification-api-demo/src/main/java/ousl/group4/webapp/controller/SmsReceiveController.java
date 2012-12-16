package ousl.group4.webapp.controller;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ousl.group4.exception.NotificationAPIException;
import ousl.group4.model.BidMax;
import ousl.group4.model.Listing;
import ousl.group4.service.BidMaxService;
import ousl.group4.service.ListingService;
import ousl.group4.sms.model.SmsKeyBox;
import ousl.group4.sms.service.SmsSender;
import ousl.group4.webapp.util.PhoneNumberDecoder;
import ousl.group4.webapp.util.SmsMessageDecoder;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Controller
public class SmsReceiveController {

    @Autowired
    private ListingService listingService;
    @Autowired
    private BidMaxService bidMaxService;
    @Autowired
    private SmsSender smsSender;

    @RequestMapping(value = "/sms-receiver.html", method = RequestMethod.GET)
    public void receiveSms(@RequestParam("phone") String phoneNumber, @RequestParam("text") String message) {
//        System.out.println("Phone number : "+ PhoneNumberDecoder.decodePhoneNumber(phoneNumber));
//        System.out.println("Message : "+ SmsMessageDecoder.decodeMessage(message));

//        Get phone number and message from URL paramaters
        String mobileNumber = PhoneNumberDecoder.decodePhoneNumber(phoneNumber);
        String sms = SmsMessageDecoder.decodeMessage(message);
        StringTokenizer tokenizer = new StringTokenizer(sms);
        String keyword = null;
        String searchBidItems = "";
        String bidSms = null;

        while (tokenizer.hasMoreElements()) {
            keyword = (String) tokenizer.nextElement();
            if (keyword.equalsIgnoreCase("list")) {
                break;
            }
            if (keyword.equalsIgnoreCase("bid")) {
                bidSms = sms;
                System.out.println(bidSms);
            }
        }

        if (keyword.equalsIgnoreCase("list")) {

            List<Listing> listingList = listingService.getFirstFiveListing();
            for (Listing listing : listingList) {
                searchBidItems = searchBidItems + listing.getId() + " " + listing.getItem().getSmsSearchName() + " " +
                        listing.getPrice() + "\n";
            }

            searchBidItems = searchBidItems + "<group4><bid><id><your bid>";
            //Generate result to send via sms and send
            Map<String, Object> smsMap = new HashMap<String, Object>();
            smsMap.put(SmsKeyBox.SENDER, "0720260442");
            smsMap.put(SmsKeyBox.RECIPIENTS, new String[]{mobileNumber});
            smsMap.put(SmsKeyBox.SMS_BODY, searchBidItems);

            try {
                smsSender.send(smsMap);
            } catch (NotificationAPIException e) {
                e.printStackTrace();
            }
        }
        String listingId = null;
        String listingPrice = null;
        StringTokenizer stringTokenizer1 = new StringTokenizer(bidSms);
        while (stringTokenizer1.hasMoreElements()) {
            if (stringTokenizer1.nextElement().toString().equalsIgnoreCase("bid")) {
                listingId = (String) stringTokenizer1.nextElement();
                listingPrice = (String) stringTokenizer1.nextElement();
                break;
            }
        }
        System.out.println(listingId);
        System.out.println(listingPrice);

        Listing listing = listingService.getListingById(Long.parseLong(listingId));
        BigDecimal maxBid = bidMaxService.getMaxBidByListingId(listing.getId());

        System.out.println(maxBid);
        if (maxBid.doubleValue() < Double.parseDouble(listingPrice)) {
            BidMax bidMax = new BidMax();
            bidMax.setListing(listing);
            bidMax.setMaxBid(new BigDecimal(Double.parseDouble(listingPrice)));

            bidMaxService.saveBidMax(bidMax);

            Map<String, Object> smsMap = new HashMap<String, Object>();
            smsMap.put(SmsKeyBox.SENDER, "0720260442");
            smsMap.put(SmsKeyBox.RECIPIENTS, new String[]{mobileNumber});
            smsMap.put(SmsKeyBox.SMS_BODY, "Congratulation ! You are the Max bidder.");

            try {
                smsSender.send(smsMap);
            } catch (NotificationAPIException e) {
                e.printStackTrace();
            }
        } else {
            Map<String, Object> smsMap = new HashMap<String, Object>();
            smsMap.put(SmsKeyBox.SENDER, "0720260442");
            smsMap.put(SmsKeyBox.RECIPIENTS, new String[]{mobileNumber});
            smsMap.put(SmsKeyBox.SMS_BODY, "Sorry ! You are out bidded.");

            try {
                smsSender.send(smsMap);
            } catch (NotificationAPIException e) {
                e.printStackTrace();
            }
        }


    }

}
