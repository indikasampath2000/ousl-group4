package ousl.group4.test;

import org.quartz.SchedulerException;
import ousl.group4.jobscheduler.MailNotificationJobRunner;

public class TestNotificationScheduler {

    public static void main(String[] args) {

        MailNotificationJobRunner mailNotificationJobRunner = new MailNotificationJobRunner();
        try {
            // start scheduler job
            mailNotificationJobRunner.initSendMailNotifications();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
