package ru.practicum.explore.except.ex;

public class EventLimitConfirmedException extends RuntimeException {
    public EventLimitConfirmedException(final String message) {
        super(message);
    }
}
