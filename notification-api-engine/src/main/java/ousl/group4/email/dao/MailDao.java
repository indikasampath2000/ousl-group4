package ousl.group4.email.dao;

import java.util.List;

import ousl.group4.email.model.Mail;
import ousl.group4.email.model.MailSchedule;

public interface MailDao {

    /**
     * @param mail
     * @return
     */
    Mail saveMail(Mail mail);

    /**
     * @param mail
     * @return
     */
    Mail updateMail(Mail mail);

    /**
     * @param mailId
     * @return
     */
    Mail getMailById(Long mailId);

    /**
     * @return
     */
    List<Mail> getPendingMailNotifications();

    /**
     *
     * @param mailSchedule
     * @return
     */
    List<Mail> getScheduleMailNotificationByMailSchedule(MailSchedule mailSchedule);
}
