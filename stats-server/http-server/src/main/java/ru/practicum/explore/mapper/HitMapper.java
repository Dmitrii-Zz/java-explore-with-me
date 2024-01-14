package ru.practicum.explore.mapper;

import ru.practicum.explore.dto.HitDto;
import ru.practicum.explore.model.Hit;

public class HitMapper {

    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .app(hitDto.getApp())
                .ip(hitDto.getIp())
                .uri(hitDto.getUri())
                .build();
    }
}
