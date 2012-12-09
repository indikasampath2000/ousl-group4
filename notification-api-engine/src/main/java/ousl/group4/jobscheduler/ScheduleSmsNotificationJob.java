package ousl.group4.jobscheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsSchedule;
import ousl.group4.sms.service.SmsScheduleService;
import ousl.group4.sms.service.SmsService;
import ousl.group4.sms.service.impl.SmsScheduleServiceImpl;
import ousl.group4.sms.service.impl.SmsServiceImpl;
import ousl.group4.workerqueue.sms.SmsAssignerThread;
import ousl.group4.workerqueue.sms.SmsQueue;
import ousl.group4.workerqueue.sms.SmsQueueFactory;

import java.util.Date;
import java.util.List;

public class ScheduleSmsNotificationJob implements Job {

    private SmsService smsService = new SmsServiceImpl();
    private SmsScheduleService smsScheduleService = new SmsScheduleServiceImpl();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobName = jobDataMap.getString("smsJobName");
        SmsSchedule mailSchedule = smsScheduleService.isScheduleJobExist(jobName);
        List<Sms> smsList = smsService.getScheduleMailNotificationByMailSchedule(mailSchedule);
        if (smsList.size() > 0) {
            for (Object o : smsList) {
                Sms sms = (Sms) o;
                sms.setQueue(true);
                smsService.updateSms(sms);
            }
            // Get Worker Queue
            SmsQueue smsQueue = SmsQueueFactory.getWorkQueue(5, smsList.size());
            // Start the mail assigner thread
            SmsAssignerThread smsAssignerThread = new SmsAssignerThread(smsList, smsQueue);
            smsAssignerThread.start();
            // Start time of job
            long startTime = System.currentTimeMillis();
            // Populate the task into sms queue
            smsQueue.startAllThreads();
            try {
                smsAssignerThread.join();
                int tasksDone = smsQueue.stopWhenAllTaskFinished();
                long endTime = System.currentTimeMillis();
                System.out.println("Total Time (sms) : " + (endTime - startTime));
                System.out.println("Number of Tasks Executed (sms) : " + tasksDone);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                smsQueue = null;
                smsAssignerThread = null;
            }

        }
    }
}
