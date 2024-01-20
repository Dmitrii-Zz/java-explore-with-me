package ru.practicum.explore.request.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.StateEvent;
import ru.practicum.explore.event.service.EventService;
import ru.practicum.explore.except.ex.*;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.mapper.RequestMapper;
import ru.practicum.explore.request.model.Request;
import ru.practicum.explore.request.model.RequestStatus;
import ru.practicum.explore.request.repository.RequestRepository;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService {
    private final static List<RequestStatus> REQUEST_STATUSES = List.of(RequestStatus.CANCELED,
            RequestStatus.REJECTED);
    private final RequestRepository requestStorage;
    private final UserService userService;
    private final EventService eventService;

    public ResponseEntity<Object> createRequest(long userId, long eventId) {
        User user = userService.checkExistsUser(userId);
        Event event = eventService.checkExistsEvent(eventId);

        if (event.getInitiator().getId() == userId) {
            throw new InitiatorEventException("Нельзя создавать заявку на собственное событие.");
        }

        checkForRepeatedRequest(userId, eventId);

        if (!event.getState().equals(StateEvent.PUBLISHED)) {
            throw new EventPublishedException("Нельзя участвовать в неопубликованном событии.");
        }

        long countRequestEvent = requestStorage.countByEventIdAndStatusIsNotIn(eventId, REQUEST_STATUSES);

        if (event.getParticipantLimit() != 0 && countRequestEvent >= event.getParticipantLimit()) {
            throw new ParticipantLimitException("Превышен лимит заявок на событие.");
        }

        Request request = Request.builder()
                .created(LocalDateTime.now())
                .event(event)
                .requester(user)
                .build();

        if (event.isRequestModeration()) {
            request.setStatus(RequestStatus.PENDING);
        } else {
            request.setStatus(RequestStatus.CONFIRMED);
        }

        eventService.changeConfirmedRequests(eventId, true);
        return new ResponseEntity<>(RequestMapper.toParticipationRequestDto(requestStorage.save(request)),
                HttpStatus.OK);
    }

    public ResponseEntity<Object> cancelRequest(long userId, long requestId) {
        User user = userService.checkExistsUser(userId);
        Request request = checkExistsRequest(requestId);

        if (!(request.getRequester().getId() == userId)) {
            throw new RequestNotFountException("Request with id=" + requestId + "was not found");
        }

        request.setStatus(RequestStatus.CANCELED);
        eventService.changeConfirmedRequests(request.getEvent().getId(), false);
        return new ResponseEntity<>(RequestMapper.toParticipationRequestDto(requestStorage.save(request)),
                HttpStatus.OK);
    }

    public ResponseEntity<Object> getRequests(long userId) {
        userService.checkExistsUser(userId);
        List<ParticipationRequestDto> requests = requestStorage.findAllByRequesterId(userId).stream()
                .map(RequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    private Request checkExistsRequest(long requestId) {
        Optional<Request> optionalRequest = requestStorage.findById(requestId);

        if (optionalRequest.isEmpty()) {
            throw new RequestNotFountException("Request with id=" + requestId + "was not found");
        }

        return optionalRequest.get();
    }

    private void checkForRepeatedRequest(long userId, long eventId) {
        Request request = requestStorage.findByRequesterIdAndEventId(userId, eventId);

        if (request != null) {
            throw new RepeatedRequestException("Заявка уже существует.");
        }
    }
}
