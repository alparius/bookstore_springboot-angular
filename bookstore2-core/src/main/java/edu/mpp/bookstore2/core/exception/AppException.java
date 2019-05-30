package edu.mpp.bookstore2.core.exception;

public class AppException extends RuntimeException{

    /**
     * Creates a custom exception with a message argument.
     * @param message not null
     * @throws AppException object
     */
    public AppException(String message) {
        super(message);
    }


    /**
     * Creates a custom exception with a message and a cause arguments.
     * @param message a message to be transmitted
     * @param cause not null
     * @throws AppException object
     */
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * Creates a custom exception with a cause argument only.
     * @param cause not null
     * @throws AppException object
     */
    public AppException(Throwable cause) {
        super(cause);
    }
}
