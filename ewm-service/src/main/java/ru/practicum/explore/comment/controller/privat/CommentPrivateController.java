package ru.practicum.explore.comment.controller.privat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.comment.dto.CommentDto;
import ru.practicum.explore.comment.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events/{eventId}/comment")
public class CommentPrivateController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Object> addComment(@PathVariable @PositiveOrZero long userId,
                                             @PathVariable @PositiveOrZero long eventId,
                                             @RequestBody @Valid CommentDto comment) {
        log.info("Запрос на создание комментария от юзера id = {}, событию id = {}", userId, eventId);
        return commentService.addComment(userId, eventId, comment);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Object> updateComment(@PathVariable @PositiveOrZero long userId,
                                                @PathVariable @PositiveOrZero long eventId,
                                                @PathVariable @PositiveOrZero long commentId,
                                                @RequestBody @Valid CommentDto comment) {
        log.info("Обновление коммента id = {} к событию id = {} от юзера id = {}", commentId, eventId, userId);
        return commentService.updateComment(userId, eventId, commentId, comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authorDeleteComment(@PathVariable @PositiveOrZero long userId,
                                    @PathVariable @PositiveOrZero long eventId,
                                    @PathVariable @PositiveOrZero long commentId) {
        log.info("Запрос на удаление коммента id = {} к событию id = {} от юзера id = {}", commentId, eventId, userId);
        commentService.deleteCommentAuthorEventOrComment(commentId, userId, eventId);
    }
}
