package ru.practicum.explore.except.ex;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(final String message) {
        super(message);
    }
}
