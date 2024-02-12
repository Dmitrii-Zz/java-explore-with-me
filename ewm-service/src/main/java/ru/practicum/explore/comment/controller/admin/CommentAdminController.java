package ru.practicum.explore.comment.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.comment.service.CommentService;

import javax.validation.constraints.PositiveOrZero;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events/{eventId}/comment/{commentId}")
public class CommentAdminController {
    private final CommentService commentService;

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @PositiveOrZero long eventId,
                              @PathVariable @PositiveOrZero long commentId) {
        log.info("Админ удаляет комментарий id = {} у события {}", commentId, eventId);
        commentService.adminDeleteComment(commentId, eventId);
    }
}
