package ousl.group4.workerqueue.email;

public class MailQueueFactory {

    private static MailQueue mailQueue;

    /**
     * private constructor
     */
    private MailQueueFactory() {
    }

    /**
     * Factory method to get MailQueue object
     *
     * @param nThreads
     * @param totalTasks
     * @return
     */
    public static MailQueue getWorkQueue(int nThreads, int totalTasks) {
        mailQueue = new MailQueueImpl(nThreads, totalTasks);
        return mailQueue;
    }
}
