package ru.practicum.explore.except.ex;

public class EventPublishedException extends RuntimeException {
    public EventPublishedException(final String message) {
        super(message);
    }
}
