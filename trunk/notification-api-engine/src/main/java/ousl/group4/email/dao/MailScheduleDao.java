package ousl.group4.email.dao;

import ousl.group4.email.model.MailSchedule;

public interface MailScheduleDao {

    /**
     * persist mail schedule
     *
     * @param mailSchedule
     */
    MailSchedule saveMailSchedule(MailSchedule mailSchedule);

    /**
     * return MailSchedule object if job already exist
     *
     * @param jobName
     * @return
     */
    MailSchedule isScheduleJobExist(String jobName);
}
