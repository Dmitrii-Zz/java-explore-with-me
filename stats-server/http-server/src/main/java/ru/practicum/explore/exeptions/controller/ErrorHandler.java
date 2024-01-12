package ru.practicum.explore.exeptions.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explore.exeptions.ex.LocalDataTimeParseException;
import ru.practicum.explore.exeptions.ex.ValidatedDataTimeException;
import ru.practicum.explore.exeptions.model.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleLocalDataTimeParseException(final LocalDataTimeParseException e) {
        log.debug("Произошла ошибка {}", e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidatedDataTimedException(final ValidatedDataTimeException e) {
        log.debug("Произошла ошибка {}", e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }
}
