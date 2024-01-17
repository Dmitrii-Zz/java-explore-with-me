package ru.practicum.explore.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NewEventDto {
    @NotNull
    @Size(min = 20, max = 2000, message = "Длина аннотации должна быть в диапазоне 20-2000.")
    private String annotation;

    @NotNull
    @Positive(message = "Значение категории не может быть отрицательным.")
    private Integer category;

    @NotNull
    @Size(min = 20, max = 7000, message = "Длина описании должна быть в диапазоне 20-7000.")
    private String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    @NotNull
    private LocationDto locationDto;

    private boolean paid;

    @PositiveOrZero
    private int participantLimit;

    private boolean requestModeration;

    @NotNull
    @Size(min = 3, max = 120, message = "Длина аннотации должна быть в диапазоне 3-120.")
    private String title;
}
