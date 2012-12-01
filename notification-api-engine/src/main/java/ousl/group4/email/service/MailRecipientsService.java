package ousl.group4.email.service;

import java.util.List;

import ousl.group4.email.model.MailRecipients;

public interface MailRecipientsService {

    /**
     * @param mailId
     * @return
     */
    List<MailRecipients> getMailRecipientsByMailId(Long mailId);

    /**
     * @param mailRecipients
     */
    void updateMailRecipients(MailRecipients mailRecipients);
}
