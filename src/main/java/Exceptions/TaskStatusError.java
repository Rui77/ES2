package Exceptions;

/**
 * Created by Rui on 22/12/2016.
 */
public class TaskStatusError extends Exception {

    public TaskStatusError() {
        super();
    }

    public TaskStatusError(String message) {
        super(message);
    }

    public TaskStatusError(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskStatusError(Throwable cause) {
        super(cause);
    }

    protected TaskStatusError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
