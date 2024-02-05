package ru.practicum.explore.except.ex;

public class RequestIncorrectlyException extends RuntimeException {
    public RequestIncorrectlyException(final String message) {
        super(message);
    }
}
