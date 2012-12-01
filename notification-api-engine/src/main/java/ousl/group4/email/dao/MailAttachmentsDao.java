package ousl.group4.email.dao;

import java.util.List;

import ousl.group4.email.model.MailAttachments;

public interface MailAttachmentsDao {

    /**
     * @param mailId
     * @return
     */
    List<MailAttachments> getMailAttachmentsByMailId(Long mailId);
}
