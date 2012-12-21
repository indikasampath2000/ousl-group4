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
     *               <p>
     *               sender : the sender mobile. cannot be null<br/>
     *               recipients : the receiver(s) mobile number (String[] array object). cannot be null.<br/>
     *               smsBody : the body of sms. cannot be null</p>
     * @see ousl.group4.sms.model.SmsKeyBox
     * @throws ousl.group4.exception.NotificationAPIException
     *
     */
    void send(Map<String, Object> smsMap) throws NotificationAPIException;

    /**
     * @param smsMap      Must contains the below key names with relevant values
     *                    <p>
     *                    sender : the sender mobile. cannot be null<br/>
     *                    recipients : the receiver(s) mobile number (String[] array object). cannot be null.<br/>
     *                    smsBody : the body of sms. cannot be null</p>
     * @see ousl.group4.sms.model.SmsKeyBox
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
     * This method return List object of schedule Sms notification
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
