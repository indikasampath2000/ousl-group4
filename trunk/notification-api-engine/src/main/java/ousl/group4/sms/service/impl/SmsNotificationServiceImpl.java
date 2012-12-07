package ousl.group4.sms.service.impl;

import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsRecipients;
import ousl.group4.sms.service.SmsNotificationService;
import ousl.group4.sms.service.SmsRecipientsService;
import ousl.group4.sms.service.SmsService;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SmsNotificationServiceImpl implements SmsNotificationService {

    private SmsService smsService = new SmsServiceImpl();
    private SmsRecipientsService smsRecipientsService = new SmsRecipientsServiceImpl();
    private Properties properties = new Properties();

    public SmsNotificationServiceImpl() {
        try {
            properties.load(getClass().getResourceAsStream("/notification-api-sms.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param sms
     * @throws Exception
     */
    @Override
    public void send(Sms sms) throws Exception {
        int failCounter = 0;
        String query = null;
        String result = "";
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        String host = (String) properties.get("sms.bearerbox.host");
        Integer port = Integer.parseInt((String) properties.get("sms.sendsms.port"));
        String smsc = (String) properties.get("sms.smsc.id");
        String username = (String) properties.get("sms.username");
        String password = (String) properties.get("sms.password");

        List<SmsRecipients> recipientsList = sms.getSmsRecipients();

        try {
            for (Object o : recipientsList) {
                SmsRecipients receiver = (SmsRecipients) o;

                query = "smsc=" + URLEncoder.encode(smsc, "UTF-8") +
                        "&from=" + URLEncoder.encode(sms.getSmsSender(), "UTF-8") +
                        "&to=" + URLEncoder.encode(receiver.getRecipientMobileNumber(), "UTF-8") +
                        "&username=" + URLEncoder.encode(username, "UTF-8") +
                        "&password=" + URLEncoder.encode(password, "UTF-8") +
                        "&text=" + URLEncoder.encode(sms.getSmsBody(), "UTF-8") +
                        "&coding=0&charset=ascii";

                URL url = new URL("http", host, port, "/cgi-bin/sendsms?" + query);

                inputStream = url.openStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                while (true) {
                    int c = bufferedInputStream.read(); // Check for EOF
                    if (c == -1) {
                        break;
                    } else {
                        result = result + (char) c;
                    }
                }

                if (result.equalsIgnoreCase("0: Accepted for delivery")) {
                    receiver.setStatus(true);
                    smsRecipientsService.updateSmsRecipients(receiver);
                }

                receiver = null;
                result = "";

                System.out.println(result + " to - " + receiver.getRecipientMobileNumber());

            }
        } catch (IOException e) {
            failCounter = failCounter + 1;
            e.printStackTrace();
        } finally {
            bufferedInputStream.close();
            inputStream.close();
            sms.setStatus(true);
            sms.setFailCount(failCounter);
            sms.setSmsSentTime(new Date());
            smsService.updateSms(sms);
        }


    }
}
