package ousl.group4.email.service;

import java.util.List;

import ousl.group4.email.model.MailInlineImages;

public interface MailInlineImagesService {

    /**
     * @param mailId
     * @return
     */
    List<MailInlineImages> getMailInlineImagesByMailId(Long mailId);
}
