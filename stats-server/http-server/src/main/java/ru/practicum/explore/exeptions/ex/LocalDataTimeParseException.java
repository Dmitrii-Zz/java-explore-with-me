package ru.practicum.explore.exeptions.ex;

public class LocalDataTimeParseException extends RuntimeException {
    public LocalDataTimeParseException(final String mess) {
        super(mess);
    }
}
