package ousl.group4.workerqueue.sms;

public interface SmsQueue {

    void startAllThreads();

    int stopWhenAllTaskFinished();

    void execute(Runnable t);

}
