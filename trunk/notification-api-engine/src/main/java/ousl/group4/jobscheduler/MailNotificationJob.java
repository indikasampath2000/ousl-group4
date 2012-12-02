package ousl.group4.jobscheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ousl.group4.email.model.Mail;
import ousl.group4.email.service.MailService;
import ousl.group4.email.service.impl.MailServiceImpl;
import ousl.group4.workerqueue.email.MailAssignerThread;
import ousl.group4.workerqueue.email.MailQueue;
import ousl.group4.workerqueue.email.MailQueueFactory;

import java.util.List;

public class MailNotificationJob implements Job {

    private MailService mailService = new MailServiceImpl();

    /**
     * Job scheduled to notifications queue and send to the users
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Mail> mailList = mailService.getPendingMailNotifications();
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
            System.out.println("Total Time (email) : " + (endTime - startTime));
            System.out.println("Number of Tasks Executed (email) : " + tasksDone);

        }
    }
}
