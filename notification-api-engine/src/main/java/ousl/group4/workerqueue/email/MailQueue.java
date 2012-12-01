package ousl.group4.workerqueue.email;

public interface MailQueue {

    void startAllThreads();

    int stopWhenAllTaskFinished();

    void execute(Runnable t);
}
