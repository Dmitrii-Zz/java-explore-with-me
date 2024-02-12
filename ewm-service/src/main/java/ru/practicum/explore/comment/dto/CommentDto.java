package ru.practicum.explore.comment.dto;

import lombok.*;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.user.dto.UserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;

    @NotBlank
    @Size(max = 1000, message = "Размер текста должен быть в диапазоне 1-1000")
    private String text;

    private EventShortDto event;

    private UserDto user;

    private long likes;

    private String created;
}
