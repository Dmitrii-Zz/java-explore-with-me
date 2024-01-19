package ru.practicum.explore.event.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventUserRequest extends NewEventDto {

    private String stateAction;
}
