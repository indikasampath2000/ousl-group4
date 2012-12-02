package ousl.group4.jobscheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Set;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class NotificationJobRunner {
    /**
     * send mail notification to users
     *
     * @throws SchedulerException
     */
    public void initSendNotifications() throws SchedulerException {
        // Retrieve a scheduler from schedule factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // Start scheduler
        scheduler.start();

        // Get the job details of mail if already exist
        JobKey mailJobKey = new JobKey("MailNotificationJob", "MailNotificationJobGroup");
        JobDetail mailNotificationJob = scheduler.getJobDetail(mailJobKey);

        // If job details not found then create new job
        if (mailNotificationJob == null) {
            // Initiate JobDetail with job name, job group, and executable job class
            JobDetail jobDetail = JobBuilder.newJob(MailNotificationJob.class)
                    .withIdentity("MailNotificationJob", "MailNotificationJobGroup")
                    .build();
            // Initiate CronTrigger with its name and group name
            CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
                    .withIdentity("MailNotificationCronTrigger", "MailNotificationCronTriggerGroup")
                    .withPriority(1)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?"))
                    .build();

            // schedule a job with JobDetail and Trigger
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }

        // Get the job details of Sms if already exist
        JobKey smsJobKey = new JobKey("SmsNotificationJob", "SmsNotificationJobGroup");
        JobDetail smsNotificationJob = scheduler.getJobDetail(smsJobKey);

        // If job details not found then create new job
        if (smsNotificationJob == null) {
            // Initiate JobDetail with job name, job group, and executable job class
            JobDetail jobDetail = JobBuilder.newJob(SmsNotificationJob.class)
                    .withIdentity("SmsNotificationJob", "SmsNotificationJobGroup")
                    .build();
            // Initiate CronTrigger with its name and group name
            CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
                    .withIdentity("SmsNotificationCronTrigger", "SmsNotificationCronTriggerGroup")
                    .withPriority(1)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
                    .build();

            // schedule a job with JobDetail and Trigger
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }

    }

}
