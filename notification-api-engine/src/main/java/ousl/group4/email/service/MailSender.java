package ousl.group4.email.service;

import java.util.List;
import java.util.Map;

import ousl.group4.email.model.Mail;
import ousl.group4.exception.NotificationAPIException;
import ousl.group4.email.model.MailSchedule;

/**
 * End users use implementation of this interface to send email
 */
public interface MailSender {

    /**
     * @param mailMap Must contains the below key names with relevant values
     *                sender : the sender email address. cannot be null
     *                recipients : the receiver(s) email addresses and send type (String[][] array object). first array element: email address. second array element: pass one of constant in MailSendType. cannot be null. refer: MailSendType
     *                subject : the subject of email. cannot be null
     *                mailBody : the body of email. cannot be null
     *                attachments : Attachment(s) if necessary (Optional). must contain absolute path of file(s) (String[] array object).
     *                inlineImages : Inline image(s) if necessary (Optional). must contain absolute path of image(s) and content id (String[][] array object).
     * @throws ousl.group4.exception.NotificationAPIException
     */
    void send(Map<String, Object> mailMap) throws NotificationAPIException;

    /**
     * @param mailMap      Must contains the below key names with relevant values
     *                     sender : the sender email address. cannot be null
     *                     recipients : the receiver(s) email addresses and send type (String[][] array object). first array element: email address. second array element: pass one of constant in MailSendType. cannot be null. refer: MailSendType
     *                     subject : the subject of email. cannot be null
     *                     attachments : Attachment(s) if necessary (Optional). must contain absolute path of file(s) (String[] array object).
     *                     inlineImages : Inline image(s) if necessary (Optional). must contain absolute path of image(s) and content id (String[][] array object).
     *                     other key value pair of velocity template's place holders, cannot be null
     * @param templatePath classpath of template. cannot be null
     * @throws Exception
     */
    void send(Map<String, Object> mailMap, String templatePath) throws Exception;

    /**
     * @param mailMap      Must contains the below key names with relevant values
     *                     sender : the sender email address. cannot be null
     *                     recipients : the receiver(s) email addresses and send type (String[][] array object). first array element: email address. second array element: pass one of constant in MailSendType. cannot be null. refer: MailSendType
     *                     subject : the subject of email. cannot be null
     *                     mailBody : the body of email. cannot be null
     *                     attachments : Attachment(s) if necessary (Optional). must contain absolute path of file(s) (String[] array object).
     *                     inlineImages : Inline image(s) if necessary (Optional). must contain absolute path of image(s) and content id (String[][] array object).
     * @param mailSchedule contains schedule job data
     * @throws Exception
     */
    void scheduleMail(Map<String, Object> mailMap, MailSchedule mailSchedule) throws NotificationAPIException;

    /**
     * @param mailMap      Must contains the below key names with relevant values
     *                     sender : the sender email address. cannot be null
     *                     recipients : the receiver(s) email addresses and send type (String[][] array object). first array element: email address. second array element: pass one of constant in MailSendType. cannot be null. refer: MailSendType
     *                     subject : the subject of email. cannot be null
     *                     attachments : Attachment(s) if necessary (Optional). must contain absolute path of file(s) (String[] array object).
     *                     inlineImages : Inline image(s) if necessary (Optional). must contain absolute path of image(s) and content id (String[][] array object).
     *                     other key value pair of velocity template's place holders, cannot be null
     * @param templatePath classpath of template. cannot be null
     * @param mailSchedule contains schedule job data
     * @throws ousl.group4.exception.NotificationAPIException
     */
    void scheduleMail(Map<String, Object> mailMap, String templatePath, MailSchedule mailSchedule) throws Exception;

    /**
     * This method return List object of finished mail notification
     *
     * @return
     */
    List<Mail> getFinishedMailNotifications();
}
