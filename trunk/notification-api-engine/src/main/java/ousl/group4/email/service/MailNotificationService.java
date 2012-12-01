package ousl.group4.email.service;

import ousl.group4.email.model.Mail;

public interface MailNotificationService {

    /**
     * @param mail
     * @throws Exception
     */
    void send(Mail mail) throws Exception;
}
