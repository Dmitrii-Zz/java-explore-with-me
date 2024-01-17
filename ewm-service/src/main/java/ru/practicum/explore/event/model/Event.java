package ru.practicum.explore.event.model;

import ru.practicum.explore.user.dto.UserShortDto;

import java.time.LocalDateTime;

public class Event {

    private long id;

    private String annotation;

    private Integer category;

    private String description;

    private LocalDateTime createdOn;

    private LocalDateTime eventDate;

    private LocalDateTime publishedOn;

    private Location location;

    private boolean paid;

    private int participantLimit;

    private int confirmedRequests;

    private UserShortDto initiator;

    private boolean requestModeration;

    private String title;

    private StateEvent state;

    private int views;
}
