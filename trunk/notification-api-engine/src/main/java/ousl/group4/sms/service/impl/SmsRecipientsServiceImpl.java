package ousl.group4.sms.service.impl;

import ousl.group4.sms.dao.SmsRecipientsDao;
import ousl.group4.sms.dao.impl.SmsRecipientsDaoImpl;
import ousl.group4.sms.model.SmsRecipients;
import ousl.group4.sms.service.SmsRecipientsService;

import java.util.List;

public class SmsRecipientsServiceImpl implements SmsRecipientsService{

    private SmsRecipientsDao smsRecipientsDao = new SmsRecipientsDaoImpl();

    /**
     * @param smsId
     * @return
     */
    @Override
    public List<SmsRecipients> getSmsRecipientsBySmsId(Long smsId) {
        return smsRecipientsDao.getSmsRecipientsBySmsId(smsId);
    }

    /**
     * @param smsRecipients
     */
    @Override
    public void updateSmsRecipients(SmsRecipients smsRecipients) {
        smsRecipientsDao.updateSmsRecipients(smsRecipients);
    }
}
