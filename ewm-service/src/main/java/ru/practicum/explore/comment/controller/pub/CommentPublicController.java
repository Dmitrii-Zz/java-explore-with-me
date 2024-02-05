package ru.practicum.explore.comment.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore.comment.service.CommentService;

import javax.validation.constraints.PositiveOrZero;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/events/{eventId}/comment")
public class CommentPublicController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Object> getAllCommentByEventId(@PathVariable @PositiveOrZero long eventId) {
        return commentService.getAllCommentByEventId();
    }
}
