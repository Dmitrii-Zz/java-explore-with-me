package ru.practicum.explore.comment.mapper;

import ru.practicum.explore.comment.dto.CommentDto;
import ru.practicum.explore.comment.model.Comment;
import ru.practicum.explore.event.mapper.EventMapper;
import ru.practicum.explore.user.mapper.UserMapper;

import java.time.format.DateTimeFormatter;

public class CommentMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Comment toComment(CommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .build();
    }

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .user(UserMapper.toUserDto(comment.getUser()))
                .event(EventMapper.toEventShortDto(comment.getEvent()))
                .created(comment.getCreated().format(FORMATTER))
                .text(comment.getText())
                .likes(comment.getLikes())
                .build();
    }
}
