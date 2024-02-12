package ru.practicum.explore.comment.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {

    private long id;

    private long commentId;

    private long authorId;
}
