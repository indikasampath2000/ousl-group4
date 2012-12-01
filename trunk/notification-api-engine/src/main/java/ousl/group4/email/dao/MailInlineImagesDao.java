package ousl.group4.email.dao;

import java.util.List;

import ousl.group4.email.model.MailInlineImages;

public interface MailInlineImagesDao {

    /**
     * @param mailId
     * @return
     */
    List<MailInlineImages> getMailInlineImagesByMailId(Long mailId);
}
