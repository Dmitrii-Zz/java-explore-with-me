package ru.practicum.explore.except.ex;

public class RequestNotFountException extends RuntimeException {
    public RequestNotFountException(final String message) {
        super(message);
    }
}
