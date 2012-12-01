package ousl.group4.email.service.impl;

import java.util.List;

import ousl.group4.email.dao.MailRecipientsDao;
import ousl.group4.email.dao.impl.MailRecipientsDaoImpl;
import ousl.group4.email.model.MailRecipients;
import ousl.group4.email.service.MailRecipientsService;

public class MailRecipientsServiceImpl implements MailRecipientsService {

    private MailRecipientsDao mailRecipientsDao = new MailRecipientsDaoImpl();

    /**
     * @param mailId
     * @return
     */
    @Override
    public List<MailRecipients> getMailRecipientsByMailId(Long mailId) {
        return mailRecipientsDao.getMailRecipientsByMailId(mailId);
    }

    /**
     * @param mailRecipients
     */
    @Override
    public void updateMailRecipients(MailRecipients mailRecipients) {
        mailRecipientsDao.updateMailRecipients(mailRecipients);
    }
}
