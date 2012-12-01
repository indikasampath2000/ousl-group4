package ousl.group4.exception;

/**
 * Custom exception class
 */
public class NotificationAPIException extends Exception {

    /**
     * Constructs a new exception with <code>null</code> as its detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public NotificationAPIException() {
    }

    /**
     * Constructs a new exception with the specified detail message. The cause is not initialized,
     * and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                {@link #getMessage()} method.
     */
    public NotificationAPIException(String message) {
        super(message);
    }
}
