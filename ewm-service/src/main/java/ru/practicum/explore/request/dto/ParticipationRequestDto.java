package ru.practicum.explore.request.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationRequestDto {

    private long id;

    private LocalDateTime created;

    private long event;

    private long requester;

    private String status;
}
