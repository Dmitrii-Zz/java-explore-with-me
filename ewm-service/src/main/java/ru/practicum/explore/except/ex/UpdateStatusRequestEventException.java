package ru.practicum.explore.except.ex;

public class UpdateStatusRequestEventException extends  RuntimeException {
    public UpdateStatusRequestEventException(final String message) {
        super(message);
    }
}
