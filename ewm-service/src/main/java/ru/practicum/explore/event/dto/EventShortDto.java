package ru.practicum.explore.event.dto;

import lombok.*;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.user.dto.UserShortDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventShortDto {
    private long id;

    private String annotation;

    private CategoryDto category;

    private String eventDate;

    private boolean paid;

    private int confirmedRequests;

    private UserShortDto initiator;

    private String title;

    private int views;
}
