package ru.practicum.explore.event.controller.privat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.EventRequestStatusUpdateRequest;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventUserRequest;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
public class EventsPrivateController {

    @GetMapping
    public ResponseEntity<Object> getEvents(@PathVariable int userId,
                                            @RequestParam(defaultValue = "0") int from,
                                            @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>("Пользователь ID = " + userId, HttpStatus.OK);
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
