package ousl.group4.workerqueue.sms;

import ousl.group4.email.model.Mail;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.service.SmsNotificationService;
import ousl.group4.sms.service.impl.SmsNotificationServiceImpl;

public class TaskController implements Runnable {

    private SmsNotificationService smsNotificationService = new SmsNotificationServiceImpl();
    private Sms sms;

    public TaskController(Sms sms) {
        this.sms = sms;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread,
     * starting the thread causes the object's <code>run</code> method to be called in that
     * separately executing thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may take any action
     * whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            smsNotificationService.send(this.sms);
        } catch (Exception e) {
            e.printStackTrace();  //TODO: add logger to print error message
        }
    }
}
