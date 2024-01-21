package ru.practicum.explore.compilation.dto;

import lombok.*;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.model.Event;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompilationDto {

    private long id;

    private Set<EventShortDto> events;

    private boolean pinned;

    private String title;
}
