package ru.practicum.explore.except.ex;

public class EventNotFountException extends RuntimeException {
    public EventNotFountException(final String message) {
        super(message);
    }
}
