package ousl.group4.workerqueue.email;

import ousl.group4.email.model.Mail;

import java.util.List;

public class MailAssignerThread extends Thread {

    private List<Mail> mailList;
    private final MailQueue mailQueue;
    private int totalTasks;
    private int initialCount;

    /**
     * MailAssignerThread constructor
     *
     * @param mailList
     * @param mailQueue
     */
    public MailAssignerThread(List<Mail> mailList, MailQueue mailQueue) {
        this.mailList = mailList;
        this.mailQueue = mailQueue;
        this.totalTasks = mailList.size();
        if(totalTasks == 1){
            this.initialCount = totalTasks;
        }else {
            this.initialCount = Math.min((totalTasks / 2), Integer.MAX_VALUE);
        }

        for (int i = 0; i < initialCount; i++) {
            TaskController taskController = new TaskController(mailList.get(i));
            mailQueue.execute(taskController);
        }

    }

    /**
     * Override Thread class method
     */
    public void run() {
        for (int i = initialCount; i < totalTasks; i++) {
            TaskController taskController = new TaskController(mailList.get(i));
            mailQueue.execute(taskController);
        }
    }
}
