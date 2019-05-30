package edu.mpp.bookstore2.core.exception;

public class ValidatorException extends RuntimeException {

    /**
     * Creates a custom exception.
     * @param message must be not null.
     * @throws ValidatorException object
     */
    public ValidatorException(String message) {
        super(message);
    }


    /**
     * Creates a custom exception.
     * @param message the message to be transmitted
     * @param cause not null
     * @throws ValidatorException object
     */
    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a custom exception.
     * @param cause must be not null.
     * @throws ValidatorException object
     */
    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
