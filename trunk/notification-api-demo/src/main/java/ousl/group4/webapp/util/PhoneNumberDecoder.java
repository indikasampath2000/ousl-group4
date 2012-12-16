package ousl.group4.webapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PhoneNumberDecoder {

    public static String decodePhoneNumber(String phoneNumber){
        try {
            return URLDecoder.decode(phoneNumber, "UTF-8").replaceFirst("94", "0").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
