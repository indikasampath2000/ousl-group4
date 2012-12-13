package ousl.group4.sms.dao;

import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsSchedule;

import java.util.List;

public interface SmsDao {

    /**
     *
     * @param sms
     * @return
     */
    Sms saveSms(Sms sms);

    /**
     *
     * @param sms
     * @return
     */
    Sms updateSms(Sms sms);

    /**
     *
     * @param smsId
     * @return
     */
    Sms getSmsById(Long smsId);

    /**
     *
     * @return
     */
    List<Sms> getPendingSmsNotifications();

    /**
     *
     * @param smsSchedule
     * @return
     */
    List<Sms> getScheduleMailNotificationByMailSchedule(SmsSchedule smsSchedule);

    /**
     *
     * @return
     */
    List<Sms> getFinishedSmsNotifications();
}
