package ru.practicum.explore.except.ex;

public class CategoryNotFountException extends RuntimeException {
    public CategoryNotFountException(final String message) {
        super(message);
    }
}
