package ousl.group4.test;

import java.util.*;
import java.util.regex.Pattern;

import ousl.group4.email.model.*;
import ousl.group4.email.service.MailScheduleService;
import ousl.group4.email.service.MailSender;
import ousl.group4.email.service.MailService;
import ousl.group4.email.service.impl.MailScheduleServiceImpl;
import ousl.group4.email.service.impl.MailSenderImpl;
import ousl.group4.email.service.impl.MailServiceImpl;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsKeyBox;
import ousl.group4.sms.service.SmsNotificationService;
import ousl.group4.sms.service.SmsSender;
import ousl.group4.sms.service.SmsService;
import ousl.group4.sms.service.impl.SmsNotificationServiceImpl;
import ousl.group4.sms.service.impl.SmsSenderImpl;
import ousl.group4.sms.service.impl.SmsServiceImpl;

public class TestNotificationApi {

    public static void main(String[] args) {
        MailSender mailSender = new MailSenderImpl();
        //MailScheduleService mailScheduleService = new MailScheduleServiceImpl();
        SmsSender smsSender = new SmsSenderImpl();


        // create mail schedule job
        String jobName = "todayJob2";
        MailSchedule mailSchedule = mailSender.isScheduleJobExist(jobName);
        if(mailSchedule == null){
            mailSchedule = new MailSchedule();
            mailSchedule.setJobName(jobName);
            mailSchedule.setScheduleType(MailKeyBox.FIRE_ONCE);
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, 15);
            calendar.set(Calendar.MINUTE, 16);
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.DAY_OF_MONTH, 28);
            calendar.set(Calendar.MONTH, 10);
            calendar.set(Calendar.YEAR, 2012);
            mailSchedule.setScheduleDateTime(calendar.getTime());
            mailSender.saveMailSchedule(mailSchedule);
        }

        String[][] mailAddress = {
                /*{"dinesh.wijesooriya@gmail.com", MailSendType.SEND_TO},
                {"pubu86@gmail.com",MailSendType.SEND_TO},
                {"erandirath88@gmail.com",MailSendType.SEND_TO},
                {"naomigarusinghe@gmail.com",MailSendType.SEND_TO},
                {"janakasampath2000@gmail.com", MailSendType.SEND_TO},
                {"chathurasampath2000@gmail.com", MailSendType.SEND_TO},*/
                //{"indikasampath2000@gmail.com", MailSendType.SEND_TO},
                {"indikasampath2000@yahoo.com", MailSendType.SEND_TO},
                {"indika@respere.com", MailSendType.SEND_BCC}
        };

        Map<String, Object> mailMap = new HashMap<String, Object>();
        mailMap.put(MailKeyBox.SENDER, "ouslgroup4@gmail.com");
        mailMap.put(MailKeyBox.SUBJECT, "Notification API: Test Email");
        mailMap.put(MailKeyBox.MAIL_BODY, "<h2>Open University of Sri Lanka</h2><p>BSE final year project. Group 4. Test Email</p>");
        mailMap.put(MailKeyBox.INLINE_IMAGES, new String[][]{{"/home/indika/acdemic/BSE/project/ousl.jpg", "ousl"}});
        mailMap.put(MailKeyBox.ATTACHMENTS, new String[]{"/home/indika/acdemic/BSE/project/test_attachment.txt"});
        mailMap.put("name", "Test User");
        mailMap.put("para", "Test velocity body with inline image");
        try {

            for (int i = 0; i < mailAddress.length; i++) {
                mailMap.put(MailKeyBox.RECIPIENTS, new String[][]{{mailAddress[i][0], mailAddress[i][1]}});
                // mail notification sender
                //mailSender.send(mailMap);
                //mailSender.send(mailMap, "/test.vm");
                //mailSender.scheduleMail(mailMap, mailSchedule);
                //System.out.println(mailAddress[i][0] + " : " + mailAddress[i][1]);
            }

            /*List<Mail> mails = mailService.getScheduleMailNotificationByMailSchedule(mailSchedule);

            for(Object o : mails){
                Mail mail = (Mail) o;
                System.out.println(mail.getMailRecipients().get(0).getMailAddress());
                System.out.println(mail.getMailSchedule().getScheduleDateTime());
                Date  date = mail.getMailSchedule().getScheduleDateTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                System.out.println(calendar.get(Calendar.SECOND));
                System.out.println(calendar.get(Calendar.MINUTE));
                System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
                System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
                System.out.println(calendar.get(Calendar.MONTH)+1);
                System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
                System.out.println(calendar.get(Calendar.YEAR));
            }*/

            /*String number = "0716424744";
            Pattern pattern = Pattern.compile("\\d*");
            if(pattern.matcher(number).matches()){
                System.out.println("valid phone number");
            }*/


            Map<String, Object> smsMap = new HashMap<String, Object>();
            smsMap.put(SmsKeyBox.SENDER, "0720260442");
            smsMap.put(SmsKeyBox.RECIPIENTS, new String[]{"0772269376", "0773596220", "0712496994"});
            smsMap.put(SmsKeyBox.SMS_BODY, "Bazinga... Test Message from Notification API");
            //smsSender.send(smsMap);



        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
