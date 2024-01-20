package ru.practicum.explore.except.ex;

public class RepeatedRequestException extends RuntimeException {
    public RepeatedRequestException(final String message) {
        super(message);
    }
}
