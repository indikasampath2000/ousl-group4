package ousl.group4.email.service.impl;

import java.util.List;

import ousl.group4.email.dao.MailAttachmentsDao;
import ousl.group4.email.dao.impl.MailAttachmentsDaoImpl;
import ousl.group4.email.model.MailAttachments;
import ousl.group4.email.service.MailAttachmentsService;

public class MailAttachmentsServiceImpl implements MailAttachmentsService {

    private MailAttachmentsDao mailAttachmentsDao = new MailAttachmentsDaoImpl();

    /**
     * @param mailId
     * @return
     */
    @Override
    public List<MailAttachments> getMailAttachmentsByMailId(Long mailId) {
        return mailAttachmentsDao.getMailAttachmentsByMailId(mailId);
    }
}
