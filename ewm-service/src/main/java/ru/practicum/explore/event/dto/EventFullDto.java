package ru.practicum.explore.event.dto;

import lombok.*;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.event.model.StateEvent;
import ru.practicum.explore.user.dto.UserShortDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventFullDto {

    private long id;

    private String annotation;

    private CategoryDto category;

    private String description;

    private String createdOn;

    private String eventDate;

    private String publishedOn;

    private LocationDto location;

    private boolean paid;

    private int participantLimit;

    private int confirmedRequests;

    private UserShortDto initiator;

    private boolean requestModeration;

    private String title;

    private StateEvent state;

    private int views;
}
