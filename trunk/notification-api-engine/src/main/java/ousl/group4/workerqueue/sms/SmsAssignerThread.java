package ousl.group4.workerqueue.sms;

import ousl.group4.sms.model.Sms;

import java.util.List;

public class SmsAssignerThread extends Thread{

    private List<Sms> smsList;
    private final SmsQueue smsQueue;
    private int totalTasks;
    private int initialCount;

    /**
     * MailAssignerThread constructor
     *
     * @param smsList
     * @param smsQueue
     */
    public SmsAssignerThread(List<Sms> smsList, SmsQueue smsQueue) {
        this.smsList = smsList;
        this.smsQueue = smsQueue;
        this.totalTasks = smsList.size();
        if(totalTasks == 1){
            this.initialCount = totalTasks;
        }else {
            this.initialCount = Math.min((totalTasks / 2), Integer.MAX_VALUE);
        }

        for (int i = 0; i < initialCount; i++) {
            TaskController taskController = new TaskController(smsList.get(i));
            smsQueue.execute(taskController);
        }

    }

    /**
     * Override Thread class method
     */
    public void run() {
        for (int i = initialCount; i < totalTasks; i++) {
            TaskController taskController = new TaskController(smsList.get(i));
            smsQueue.execute(taskController);
        }
    }
}
