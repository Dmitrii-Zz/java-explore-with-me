package ru.practicum.explore.comment.mapper;

import ru.practicum.explore.comment.dto.LikeDto;
import ru.practicum.explore.comment.model.Like;

public class LikeMapper {
    public static LikeDto toLikeDto(Like like) {
        return LikeDto.builder()
                .id(like.getId())
                .authorId(like.getUser().getId())
                .commentId(like.getComment().getId())
                .build();
    }
}
