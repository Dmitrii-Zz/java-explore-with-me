package ru.practicum.explore.except.ex;

public class ParticipantLimitException extends RuntimeException {
    public ParticipantLimitException(final String message) {
        super(message);
    }
}
