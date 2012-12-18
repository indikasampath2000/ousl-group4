package ousl.group4.sms.dao;

import ousl.group4.sms.model.SmsSchedule;

public interface SmsScheduleDao {

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
