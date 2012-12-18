package ousl.group4.sms.service.impl;

import ousl.group4.sms.dao.SmsScheduleDao;
import ousl.group4.sms.dao.impl.SmsScheduleDaoImpl;
import ousl.group4.sms.model.SmsSchedule;
import ousl.group4.sms.service.SmsScheduleService;

public class SmsScheduleServiceImpl implements SmsScheduleService{

    private SmsScheduleDao smsScheduleDao = new SmsScheduleDaoImpl();

    /**
     * persist sms schedule
     *
     * @param smsSchedule
     */
    @Override
    public SmsSchedule saveSmsSchedule(SmsSchedule smsSchedule) {
        return smsScheduleDao.saveSmsSchedule(smsSchedule);
    }

    /**
     * return SmsSchedule object if job already exist
     *
     * @param jobName
     * @return
     */
    @Override
    public SmsSchedule isScheduleJobExist(String jobName) {
        return smsScheduleDao.isScheduleJobExist(jobName);
    }
}
