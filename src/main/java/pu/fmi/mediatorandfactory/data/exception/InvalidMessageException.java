package pu.fmi.mediatorandfactory.data.exception;

public class InvalidMessageException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Invalid message format. Please provide messages with format: \"[<username>]: <message>\"";

    public InvalidMessageException() {
        this(EXCEPTION_MESSAGE);
    }

    public InvalidMessageException(String message) {
        super(message);
    }
}