package ru.practicum.explore.except.ex;

public class EventIncorectException extends RuntimeException {
    public EventIncorectException(final String message) {
        super(message);
    }
}
