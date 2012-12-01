package ousl.group4.email.service;

import java.util.List;

import ousl.group4.email.model.MailAttachments;

public interface MailAttachmentsService {

    /**
     * @param mailId
     * @return
     */
    List<MailAttachments> getMailAttachmentsByMailId(Long mailId);
}
