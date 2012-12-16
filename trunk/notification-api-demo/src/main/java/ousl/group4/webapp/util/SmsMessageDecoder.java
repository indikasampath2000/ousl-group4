package ousl.group4.webapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SmsMessageDecoder {

    public static String decodeMessage(String message){
        try {
            return URLDecoder.decode(message.toLowerCase().replaceFirst("group4", ""), "UTF-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
