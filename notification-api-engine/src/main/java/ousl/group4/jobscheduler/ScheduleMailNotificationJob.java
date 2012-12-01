package ousl.group4.jobscheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ousl.group4.email.model.Mail;
import ousl.group4.email.model.MailSchedule;
import ousl.group4.email.service.MailScheduleService;
import ousl.group4.email.service.MailService;
import ousl.group4.email.service.impl.MailScheduleServiceImpl;
import ousl.group4.email.service.impl.MailServiceImpl;
import ousl.group4.workerqueue.email.MailAssignerThread;
import ousl.group4.workerqueue.email.MailQueue;
import ousl.group4.workerqueue.email.MailQueueFactory;

import java.util.Date;
import java.util.List;

public class ScheduleMailNotificationJob implements Job{

    private MailService mailService = new MailServiceImpl();
    private MailScheduleService mailScheduleService = new MailScheduleServiceImpl();
    /**
     * Job scheduled by client
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobName = jobDataMap.getString("jobName");
        MailSchedule mailSchedule = mailScheduleService.isScheduleJobExist(jobName);
        List<Mail> mailList = mailService.getScheduleMailNotificationByMailSchedule(mailSchedule);
        if (mailList.size() > 0) {
            for (Object o : mailList) {
                Mail mail = (Mail) o;
                mail.setQueue(true);
                mailService.updateMail(mail);
            }
            // Get Worker Queue
            MailQueue mailQueue = MailQueueFactory.getWorkQueue(5, mailList.size());
            // Start the mail assigner thread
            MailAssignerThread mailAssignerThread = new MailAssignerThread(mailList, mailQueue);
            mailAssignerThread.start();
            System.out.println("Schedule job start at : " + new Date());
            // Start time of job
            long startTime = System.currentTimeMillis();
            // Populate the task into mail queue
            mailQueue.startAllThreads();
            try {
                mailAssignerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int tasksDone = mailQueue.stopWhenAllTaskFinished();
            long endTime = System.currentTimeMillis();
            System.out.println("Total Time : " + (endTime - startTime));
            System.out.println("Number of Tasks Executed : " + tasksDone);

        }
    }
}
