package ousl.group4.sms.service.impl;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ousl.group4.exception.NotificationAPIException;
import ousl.group4.jobscheduler.ScheduleSmsNotificationJob;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsKeyBox;
import ousl.group4.sms.model.SmsRecipients;
import ousl.group4.sms.model.SmsSchedule;
import ousl.group4.sms.service.SmsSender;
import ousl.group4.sms.service.SmsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SmsSenderImpl implements SmsSender {

    private SmsService smsService = new SmsServiceImpl();

    /**
     * @param smsMap Must contains the below key names with relevant values
     *               sender : the sender mobile. cannot be null
     *               recipients : the receiver(s) mobile number (String[] array object). cannot be null.
     *               smsBody : the body of sms. cannot be null
     * @throws ousl.group4.exception.NotificationAPIException
     *
     */
    @Override
    public void send(Map<String, Object> smsMap) throws NotificationAPIException {
        String sender = (String) smsMap.get("sender");
        if (sender == null || sender.isEmpty()) {
            throw new NotificationAPIException("sender is null or empty");
        }
        String[] recipients = (String[]) smsMap.get("recipients");
        if (recipients == null || recipients.length < 1) {
            throw new NotificationAPIException("recipients array is null or empty");
        }
        for (int i = 0; i < recipients.length; i++) {
            if (recipients[i].isEmpty()) {
                throw new NotificationAPIException("recipient is null or empty");
            }
        }
        String smsBody = (String) smsMap.get("mailBody");
        if (smsBody == null) {
            throw new NotificationAPIException("sms body is null");
        }

        Sms sms = new Sms();
        sms.setSmsSender(sender);
        List<SmsRecipients> smsRecipients = new ArrayList<SmsRecipients>();
        for (int i = 0; i < recipients.length; i++) {
            SmsRecipients receiver = new SmsRecipients();
            receiver.setStatus(false);
            receiver.setSms(sms);
            receiver.setRecipientMobileNumber(recipients[i]);
            smsRecipients.add(receiver);
        }
        sms.setSmsRecipients(smsRecipients);
        sms.setSmsBody(smsBody);
        sms.setCreatedOn(new Date());
        sms.setStatus(false);
        sms.setQueue(false);
        smsService.saveSms(sms);
    }

    /**
     * @param smsMap      Must contains the below key names with relevant values
     *                    sender : the sender mobile. cannot be null
     *                    recipients : the receiver(s) mobile number (String[] array object). cannot be null.
     *                    smsBody : the body of sms. cannot be null
     * @param smsSchedule contains schedule job data
     * @throws Exception
     */
    @Override
    public void scheduleMail(Map<String, Object> smsMap, SmsSchedule smsSchedule) throws NotificationAPIException {
        String sender = (String) smsMap.get("sender");
        if (sender == null || sender.isEmpty()) {
            throw new NotificationAPIException("sender is null or empty");
        }
        String[] recipients = (String[]) smsMap.get("recipients");
        if (recipients == null || recipients.length < 1) {
            throw new NotificationAPIException("recipients array is null or empty");
        }
        for (int i = 0; i < recipients.length; i++) {
            if (recipients[i].isEmpty()) {
                throw new NotificationAPIException("recipient is null or empty");
            }
        }
        String smsBody = (String) smsMap.get("mailBody");
        if (smsBody == null) {
            throw new NotificationAPIException("sms body is null");
        }

        Sms sms = new Sms();
        sms.setSmsSender(sender);
        List<SmsRecipients> smsRecipients = new ArrayList<SmsRecipients>();
        for (int i = 0; i < recipients.length; i++) {
            SmsRecipients receiver = new SmsRecipients();
            receiver.setStatus(false);
            receiver.setSms(sms);
            receiver.setRecipientMobileNumber(recipients[i]);
            smsRecipients.add(receiver);
        }

        if (smsSchedule.getJobName() == null || smsSchedule.getJobName().isEmpty()) {
            throw new NotificationAPIException("job name null or empty");
        }
        if (smsSchedule.getScheduleType() == null || smsSchedule.getScheduleType().isEmpty()) {
            throw new NotificationAPIException("schedule type null or empty");
        }
        if (smsSchedule.getScheduleType() != null) {
            if (smsSchedule.getScheduleType().equalsIgnoreCase(SmsKeyBox.FIRE_ONCE)) {
                if (smsSchedule.getScheduleDateTime() == null) {
                    throw new NotificationAPIException("schedule date null");
                }
            } else {
                if (smsSchedule.getScheduleType().equalsIgnoreCase(SmsKeyBox.FIRE_REPEAT)) {
                    if (smsSchedule.getCronExpression() == null) {
                        throw new NotificationAPIException("cron expression null");
                    }
                } else {
                    throw new NotificationAPIException("Invalid schedule type");
                }
            }
        }

        // Retrieve a scheduler from schedule factory
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            // Start scheduler
            if(scheduler.isShutdown()){
                scheduler.start();
            }

            // Get the job details if already exist
            JobKey key = new JobKey(smsSchedule.getJobName(), smsSchedule.getJobName()+"Group");
            JobDetail smsNotificationJob = scheduler.getJobDetail(key);

            // If job details not found then create new job
            if (smsNotificationJob == null) {
                // Initiate JobDetail with job name, job group, and executable job class
                JobDetail jobDetail = JobBuilder.newJob(ScheduleSmsNotificationJob.class)
                        .withIdentity(smsSchedule.getJobName(), smsSchedule.getJobName()+"Group")
                        .build();

                // set job to execute
                jobDetail.getJobDataMap().put("jobName", smsSchedule.getJobName());

                if(smsSchedule.getScheduleType().equalsIgnoreCase(SmsKeyBox.FIRE_ONCE)){
                    SimpleTrigger simpleTrigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                            .withIdentity(smsSchedule.getJobName()+"SimpleTrigger", smsSchedule.getJobName()+"SimpleTriggerGroup")
                            .startAt(smsSchedule.getScheduleDateTime())
                            .build();

                    // schedule a job with JobDetail and Trigger
                    scheduler.scheduleJob(jobDetail, simpleTrigger);
                }

                if(smsSchedule.getScheduleType().equalsIgnoreCase(SmsKeyBox.FIRE_REPEAT)){
                    // Initiate CronTrigger with its name and group name
                    CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
                            .withIdentity(smsSchedule.getJobName()+"CronTrigger", smsSchedule.getJobName()+"CronTriggerGroup")
                            .withPriority(1)
                            .withSchedule(CronScheduleBuilder.cronSchedule(smsSchedule.getCronExpression()))
                            .build();

                    // schedule a job with JobDetail and Trigger
                    scheduler.scheduleJob(jobDetail, cronTrigger);
                }

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        sms.setSmsSchedule(smsSchedule);
        sms.setSmsRecipients(smsRecipients);
        sms.setSmsBody(smsBody);
        sms.setCreatedOn(new Date());
        sms.setStatus(false);
        sms.setQueue(false);
        smsService.saveSms(sms);
    }
}
