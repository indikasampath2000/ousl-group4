package ousl.group4.sms.service;

import ousl.group4.sms.model.SmsSchedule;

public interface SmsScheduleService {

    /**
     * persist sms schedule
     *
     * @param smsSchedule
     */
    SmsSchedule saveMailSchedule(SmsSchedule smsSchedule);

    /**
     * return SmsSchedule object if job already exist
     *
     * @param jobName
     * @return
     */
    SmsSchedule isScheduleJobExist(String jobName);
}
