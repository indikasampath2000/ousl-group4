package ousl.group4.email.service.impl;


import ousl.group4.email.dao.MailScheduleDao;
import ousl.group4.email.dao.impl.MailScheduleDaoImpl;
import ousl.group4.email.model.MailSchedule;
import ousl.group4.email.service.MailScheduleService;

public class MailScheduleServiceImpl implements MailScheduleService{

    private MailScheduleDao mailScheduleDao = new MailScheduleDaoImpl();

    /**
     * persist mail schedule
     *
     * @param mailSchedule
     */
    @Override
    public void saveMailSchedule(MailSchedule mailSchedule) {
        mailScheduleDao.saveMailSchedule(mailSchedule);
    }

    /**
     * return MailSchedule object if job already exist
     *
     * @param jobName
     * @return
     */
    @Override
    public MailSchedule isScheduleJobExist(String jobName) {
        return mailScheduleDao.isScheduleJobExist(jobName);
    }
}
