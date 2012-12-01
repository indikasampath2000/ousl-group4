package ousl.group4.jobscheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Set;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class MailNotificationJobRunner {
    /**
     * send mail notification to users
     *
     * @throws SchedulerException
     */
    public void initSendMailNotifications() throws SchedulerException {
        // Retrieve a scheduler from schedule factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // Start scheduler
        scheduler.start();

        // Get the job details if already exist
        JobKey key = new JobKey("MailNotificationJob", "MailNotificationJobGroup");
        JobDetail mailNotificationJob = scheduler.getJobDetail(key);

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

    }

}
