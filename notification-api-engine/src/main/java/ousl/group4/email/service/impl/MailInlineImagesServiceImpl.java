package ousl.group4.email.service.impl;

import java.util.List;

import ousl.group4.email.dao.MailInlineImagesDao;
import ousl.group4.email.dao.impl.MailInlineImagesDaoImpl;
import ousl.group4.email.model.MailInlineImages;
import ousl.group4.email.service.MailInlineImagesService;

public class MailInlineImagesServiceImpl implements MailInlineImagesService {

    private MailInlineImagesDao mailInlineImagesDao = new MailInlineImagesDaoImpl();

    /**
     * @param mailId
     * @return
     */
    @Override
    public List<MailInlineImages> getMailInlineImagesByMailId(Long mailId) {
        return mailInlineImagesDao.getMailInlineImagesByMailId(mailId);
    }
}
