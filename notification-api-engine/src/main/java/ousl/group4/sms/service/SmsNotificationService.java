package ousl.group4.sms.service;

import ousl.group4.sms.model.Sms;

public interface SmsNotificationService {

    /**
     *
     * @param sms
     * @throws Exception
     */
    void send(Sms sms) throws Exception;
}
