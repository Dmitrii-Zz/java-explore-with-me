package ru.practicum.explore.except.ex;

public class UserNotFountException extends RuntimeException {
    public UserNotFountException(final String message) {
        super(message);
    }
}
