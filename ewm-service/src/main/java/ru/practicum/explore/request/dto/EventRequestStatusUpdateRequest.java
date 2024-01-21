package ru.practicum.explore.request.dto;

import lombok.*;
import ru.practicum.explore.request.model.RequestStatus;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateRequest {

    private List<Long> requestIds;

    private RequestStatus status;
}
