package ousl.group4.email.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import ousl.group4.email.model.Mail;
import ousl.group4.email.model.MailAttachments;
import ousl.group4.email.model.MailInlineImages;
import ousl.group4.email.model.MailRecipients;
import ousl.group4.email.service.*;

public class MailNotificationServiceImpl implements MailNotificationService {

    private MailService mailService = new MailServiceImpl();
    private MailRecipientsService mailRecipientsService = new MailRecipientsServiceImpl();
    private Properties properties = new Properties();

    public MailNotificationServiceImpl() {
        try {
            properties.load(getClass().getResourceAsStream("/notification-api.properties"));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: write a logger here 'notification-api.properties not found.'
        }

    }

    /**
     * @param mail
     * @throws Exception
     */
    @Override
    public void send(Mail mail) throws Exception {
        int failCounter = 0;
        final String user = (String) properties.get("mail.smtp.user");
        final String password = (String) properties.get("mail.smtp.password");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        // create MIME message
        MimeMessage message = new MimeMessage(session);
        // set sender
        message.setFrom(new InternetAddress(mail.getMailSender()));
        // set subject
        message.setSubject(mail.getMailSubject());
        // create Multipart object and add MimeBodyPart objects to this object
        Multipart multipart = new MimeMultipart();
        // create BodyPart object and set message content
        BodyPart messageBody = new MimeBodyPart();
        String mailBody = mail.getMailBody();
        // create MimeBodyPart object and set inline images
        List<MailInlineImages> mailInlineImagesList = mail.getMailInlineImages();
        for (Object inlineImage : mailInlineImagesList) {
            MimeBodyPart messageInlineImages = new MimeBodyPart();
            MailInlineImages mailInlineImages = (MailInlineImages) inlineImage;
            String image = mailInlineImages.getImageWithAbsoluteFilePath();
            String contentId = mailInlineImages.getContentId();
            DataSource source = new FileDataSource(image);
            messageInlineImages.setDataHandler(new DataHandler(source));
            messageInlineImages.setFileName(source.getName());
            messageInlineImages.setDisposition(MimeBodyPart.INLINE);
            messageInlineImages.setHeader("Content-ID", "<" + contentId + ">");
            multipart.addBodyPart(messageInlineImages);
            messageInlineImages = null;
            mailInlineImages = null;
            source = null;
            if(!mail.getVelocity()){
                mailBody += "<p><img src=\"cid:" + contentId + "\"></p>";
            }
        }
        messageBody.setContent(mailBody, "text/html");
        multipart.addBodyPart(messageBody);
        // create new MimeBodyPart object and set DataHandler object to this object
        MimeBodyPart messageAttachments = new MimeBodyPart();
        List<MailAttachments> mailAttachmentsList = mail.getMailAttachments();
        for (Object attachment : mailAttachmentsList) {
            MailAttachments mailAttachments = (MailAttachments) attachment;
            String file = mailAttachments.getFileWithAbsoluteFilePath();
            DataSource source = new FileDataSource(file);
            messageAttachments.setDataHandler(new DataHandler(source));
            messageAttachments.setFileName(source.getName());
            messageAttachments.setDisposition(MimeBodyPart.ATTACHMENT);
            multipart.addBodyPart(messageAttachments);
            file = null;
            source = null;
        }
        List<MailRecipients> recipientsList = mail.getMailRecipients();
        try {
            // compose message to each recipient
            for (Object recipient : recipientsList) {
                MailRecipients receiver = (MailRecipients) recipient;
                if (receiver.getMailSendType() == 1) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver.getMailAddress()));
                } else if (receiver.getMailSendType() == 2) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(receiver.getMailAddress()));
                } else if (receiver.getMailSendType() == 3) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(receiver.getMailAddress()));
                }
                // update mail recipient status
                receiver.setStatus(true);
                mailRecipientsService.updateMailRecipients(receiver);
                receiver = null;
            }
            // set the multipart object to the message object
            message.setContent(multipart);
            // send message
            Transport.send(message);
            // print delivery recipient email address
            for (Object deliveredRecipient : recipientsList) {
                MailRecipients receiver = (MailRecipients) deliveredRecipient;
                if (receiver.getMailSendType() == 1) {
                    System.out.println("TO : " + receiver.getMailAddress());
                } else if (receiver.getMailSendType() == 2) {
                    System.out.println("CC : " + receiver.getMailAddress());
                } else if (receiver.getMailSendType() == 3) {
                    System.out.println("BCC : " + receiver.getMailAddress());
                }
            }
        } catch (MessagingException e) {
            failCounter = failCounter + 1;
            e.printStackTrace();  //TODO: log error message
        } finally {
            // update mail status and fail counter
            mail.setStatus(true);
            mail.setFailCount(failCounter);
            mail.setMailSentTime(new Date());
            mailService.updateMail(mail);
        }
    }
}
