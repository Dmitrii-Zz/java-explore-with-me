package ru.practicum.explore.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.comment.dto.CommentDto;
import ru.practicum.explore.comment.mapper.CommentMapper;
import ru.practicum.explore.comment.model.Comment;
import ru.practicum.explore.comment.repository.CommentRepository;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.StateEvent;
import ru.practicum.explore.event.service.EventService;
import ru.practicum.explore.except.ex.CommentNotFoundException;
import ru.practicum.explore.except.ex.EventNotFountException;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentStorage;
    private final UserService userService;
    private final EventService eventService;

    public ResponseEntity<Object> addComment(long userId, long eventId, CommentDto commentDto) {
        User user = userService.checkExistsUser(userId);
        Event event = eventService.checkExistsEvent(eventId);

        if (!event.getState().equals(StateEvent.PUBLISHED)) {
            throw new EventNotFountException("Event with id = " + eventId + " was not found");
        }

        Comment comment = CommentMapper.toComment(commentDto);
        comment.setUser(user);
        comment.setEvent(event);
        comment.setCreated(LocalDateTime.now());
        commentStorage.save(comment);

        return new ResponseEntity<>(CommentMapper.toCommentDto(comment), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateComment(long userId, long eventId, long commentId, CommentDto commentDto) {
        Comment comment = checkExistsComment(commentId);
        userService.checkExistsUser(userId);

        Event event = eventService.checkExistsEvent(eventId);

        if (!event.getState().equals(StateEvent.PUBLISHED)) {
            throw new EventNotFountException("Event with id = " + eventId + " was not found");
        }

        if (comment.getUser().getId() != userId) {
            throw new CommentNotFoundException("Comment with id= " + commentId + " was not found");
        }

        if (commentDto.getText() != null) {
            comment.setText(commentDto.getText());
        }

        CommentDto updateCommentDto = CommentMapper.toCommentDto(commentStorage.save(comment));
        return new ResponseEntity<>(updateCommentDto, HttpStatus.OK);
    }

    public void deleteCommentAuthorEventOrComment(long commentId, long userId, long eventId) {
        Comment comment = checkExistsComment(commentId);
        userService.checkExistsUser(userId);
        Event event = eventService.checkExistsEvent(eventId);

        if (!event.getState().equals(StateEvent.PUBLISHED)) {
            throw new EventNotFountException("Event with id = " + eventId + " was not found");
        }

        if ((comment.getUser().getId() != userId) || (event.getInitiator().getId() != userId)) {
            throw new CommentNotFoundException("Comment with id = " + commentId + " was not found");
        }

        commentStorage.deleteById(commentId);
    }

    public void adminDeleteComment(long commentId, long userId) {
        checkExistsComment(commentId);
        userService.checkExistsUser(userId);
        commentStorage.deleteById(commentId);
    }

    public ResponseEntity<Object> getAllCommentByEventId() {
        return null;
    }

    public ResponseEntity<Object> addLikeComment(long userId, long commentId, long eventId) {
        return null;
    }

    public ResponseEntity<Object> deleteLikeComment(long userId, long commentId, long eventId) {
        return null;
    }

    public Comment checkExistsComment(long commentId) {
        Optional<Comment> comment = commentStorage.findById(commentId);

        if (comment.isEmpty()) {
            throw new CommentNotFoundException("Comment with id= " + commentId + " was not found");
        }

        return comment.get();
    }
}
