package ru.practicum.explore.event.dto;

import lombok.*;
import ru.practicum.explore.event.model.StateAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventUserRequest extends NewEventDto {

    private StateAction stateAction;
}
