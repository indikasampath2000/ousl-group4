package ousl.group4.email.service;

import java.util.List;

import ousl.group4.email.model.Mail;
import ousl.group4.email.model.MailSchedule;

public interface MailService {

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
     * @return
     */
    List<Mail> getPendingMailNotifications();

    /**
     *
     * @param mailSchedule
     * @return
     */
    List<Mail> getScheduleMailNotificationByMailSchedule(MailSchedule mailSchedule);

    /**
     *
     * @return
     */
    List<Mail> getFinishedMailNotifications();
}
