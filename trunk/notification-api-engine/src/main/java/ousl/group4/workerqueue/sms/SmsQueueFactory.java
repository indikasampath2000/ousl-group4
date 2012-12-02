package ousl.group4.workerqueue.sms;

public class SmsQueueFactory {

    private static SmsQueue smsQueue;

    /**
     * private constructor
     */
    private SmsQueueFactory() {
    }

    /**
     * Factory method to get SmsQueue object
     *
     * @param nThreads
     * @param totalTasks
     * @return
     */
    public static SmsQueue getWorkQueue(int nThreads, int totalTasks) {
        smsQueue = new SmsQueueImpl(nThreads, totalTasks);
        return smsQueue;
    }
}
