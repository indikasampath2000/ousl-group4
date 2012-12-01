package ousl.group4.workerqueue.email;

import ousl.group4.email.model.Mail;
import ousl.group4.email.service.MailNotificationService;
import ousl.group4.email.service.impl.MailNotificationServiceImpl;

public class TaskController implements Runnable {

    private MailNotificationService mailNotificationService = new MailNotificationServiceImpl();
    private Mail mail;

    public TaskController(Mail mail) {
        this.mail = mail;
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
            mailNotificationService.send(this.mail);
        } catch (Exception e) {
            e.printStackTrace();  //TODO: add logger to print error message
        }
    }
}
