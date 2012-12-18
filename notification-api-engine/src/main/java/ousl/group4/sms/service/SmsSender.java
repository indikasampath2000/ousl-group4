package ousl.group4.sms.service;

import ousl.group4.exception.NotificationAPIException;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsSchedule;

import java.util.List;
import java.util.Map;

/**
 * End users use implementation of this interface to send sms
 */
public interface SmsSender {

    /**
     * @param smsMap Must contains the below key names with relevant values
     *               sender : the sender mobile. cannot be null
     *               recipients : the receiver(s) mobile number (String[] array object). cannot be null.
     *               smsBody : the body of sms. cannot be null
     * @throws ousl.group4.exception.NotificationAPIException
     *
     */
    void send(Map<String, Object> smsMap) throws NotificationAPIException;

    /**
     * @param smsMap      Must contains the below key names with relevant values
     *                    sender : the sender mobile. cannot be null
     *                    recipients : the receiver(s) mobile number (String[] array object). cannot be null.
     *                    smsBody : the body of sms. cannot be null
     * @param smsSchedule contains schedule job data
     * @throws Exception
     */
    void scheduleSms(Map<String, Object> smsMap, SmsSchedule smsSchedule) throws NotificationAPIException;

    /**
     * This method return List object of finished Sms notification
     *
     * @return
     */
    List<Sms> getFinishedSmsNotifications();

    /**
     *
     * @return
     */
    List<Sms> getAllScheduleMailNotifications();

    /**
     * persist sms schedule
     *
     * @param smsSchedule
     */
    SmsSchedule saveSmsSchedule(SmsSchedule smsSchedule);

    /**
     * return SmsSchedule object if job already exist
     *
     * @param jobName
     * @return
     */
    SmsSchedule isScheduleJobExist(String jobName);
}
