package Exceptions;

/**
 * Created by Rui on 21/12/2016.
 */
public class TaskQuantityError extends Exception {

    public TaskQuantityError() {
        super();
    }

    public TaskQuantityError(String message) {
        super(message);
    }

    public TaskQuantityError(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskQuantityError(Throwable cause) {
        super(cause);
    }

    protected TaskQuantityError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
