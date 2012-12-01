package ousl.group4.email.service;

import ousl.group4.email.model.MailSchedule;

public interface MailScheduleService {

    /**
     * persist mail schedule
     *
     * @param mailSchedule
     */
    void saveMailSchedule(MailSchedule mailSchedule);

    /**
     * return MailSchedule object if job already exist
     *
     * @param jobName
     * @return
     */
    MailSchedule isScheduleJobExist(String jobName);
}
