package ousl.group4.test;

import org.quartz.SchedulerException;
import ousl.group4.jobscheduler.NotificationJobRunner;

public class TestNotificationScheduler {

    public static void main(String[] args) {

        NotificationJobRunner notificationJobRunner = new NotificationJobRunner();
        try {
            // start scheduler job
            notificationJobRunner.initSendNotifications();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
