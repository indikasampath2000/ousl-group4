package ousl.group4.email.dao;

import java.util.List;

import ousl.group4.email.model.MailRecipients;

public interface MailRecipientsDao {

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
