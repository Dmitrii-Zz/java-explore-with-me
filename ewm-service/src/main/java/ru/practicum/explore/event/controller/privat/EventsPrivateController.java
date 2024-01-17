package ru.practicum.explore.event.controller.privat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.EventRequestStatusUpdateRequest;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventUserRequest;
import ru.practicum.explore.event.service.EventService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class EventsPrivateController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Object> getEvents(@RequestBody NewEventDto newEventDto,
                                            @PathVariable @Positive int userId,
                                            @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                            @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Запрос событий юзера id = {}, параметры from = {}, size = {}", userId, from, size);
        return new ResponseEntity<>(newEventDto, HttpStatus.OK);
        //return eventService.getEvent(userId, from, size);
    }

    @PostMapping
    public ResponseEntity<Object> createEvent(@PathVariable int userId,
                                              @RequestBody NewEventDto newEventDto) {
        return null;
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Object> getEventById(@PathVariable int userId,
                                               @PathVariable int eventId) {
        return null;
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<Object> updateEvent(@PathVariable int userId,
                                              @PathVariable int eventId,
                                              @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        return null;
    }

    @GetMapping("/{eventId}/requests")
    public ResponseEntity<Object> getRequests(@PathVariable int userId,
                                              @PathVariable int eventId) {
        return null;
    }

    @PatchMapping("/{eventId}/requests")
    public ResponseEntity<Object> updateStatusRequests(
                        @PathVariable int userId,
                        @PathVariable int eventId,
                        @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return null;
    }
}
