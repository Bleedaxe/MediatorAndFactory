package pu.fmi.mediatorandfactory.data.exception;

public class InvalidUserException extends RuntimeException {

    private static final String INVALID_USER_MESSAGE_FORMAT = "User with username %s is not found!";

    public InvalidUserException(String username) {
        super(String.format(INVALID_USER_MESSAGE_FORMAT, username));
    }
}
