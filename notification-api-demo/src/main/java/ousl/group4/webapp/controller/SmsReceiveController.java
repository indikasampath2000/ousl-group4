package ousl.group4.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ousl.group4.webapp.util.PhoneNumberDecoder;
import ousl.group4.webapp.util.SmsMessageDecoder;

import java.util.StringTokenizer;

@Controller
public class SmsReceiveController {

    @RequestMapping(value = "/sms-receiver.html", method = RequestMethod.GET)
    public void receiveSms(@RequestParam("phone")String phoneNumber, @RequestParam("text")String message) {
        System.out.println("Phone number : "+ PhoneNumberDecoder.decodePhoneNumber(phoneNumber));
        System.out.println("Message : "+ SmsMessageDecoder.decodeMessage(message));

        String sms = SmsMessageDecoder.decodeMessage(message);
        StringTokenizer tokenizer = new StringTokenizer(sms);

        while (tokenizer.hasMoreElements()){
            System.out.println(tokenizer.nextElement());
        }
    }

}
