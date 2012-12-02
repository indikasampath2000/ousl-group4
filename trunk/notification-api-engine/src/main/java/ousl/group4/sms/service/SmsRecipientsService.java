package ousl.group4.sms.service;

import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsRecipients;

import java.util.List;

public interface SmsRecipientsService {
    /**
     *
     * @param smsId
     * @return
     */
    List<SmsRecipients> getSmsRecipientsBySmsId(Long smsId);

    /**
     *
     * @param smsRecipients
     */
    void updateSmsRecipients(SmsRecipients smsRecipients);
}
