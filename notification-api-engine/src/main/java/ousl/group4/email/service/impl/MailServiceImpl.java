package ousl.group4.email.service.impl;

import java.util.List;

import ousl.group4.email.dao.MailDao;
import ousl.group4.email.dao.impl.MailDaoImpl;
import ousl.group4.email.model.Mail;
import ousl.group4.email.model.MailSchedule;
import ousl.group4.email.service.MailService;

public class MailServiceImpl implements MailService {

    private MailDao mailDao = new MailDaoImpl();

    /**
     * @param mail
     * @return
     */
    @Override
    public Mail saveMail(Mail mail) {
        return mailDao.saveMail(mail);
    }

    /**
     * @param mail
     * @return
     */
    @Override
    public Mail updateMail(Mail mail) {
        return mailDao.updateMail(mail);
    }

    /**
     * @return
     */
    @Override
    public List<Mail> getPendingMailNotifications() {
        return mailDao.getPendingMailNotifications();
    }

    /**
     * @param mailSchedule
     * @return
     */
    @Override
    public List<Mail> getScheduleMailNotificationByMailSchedule(MailSchedule mailSchedule) {
        return mailDao.getScheduleMailNotificationByMailSchedule(mailSchedule);
    }

    /**
     * @return
     */
    @Override
    public List<Mail> getFinishedMailNotifications() {
        return mailDao.getFinishedMailNotifications();
    }

    /**
     * @return
     */
    @Override
    public List<Mail> getAllScheduleMailNotifications() {
        return mailDao.getAllScheduleMailNotifications();
    }
}
