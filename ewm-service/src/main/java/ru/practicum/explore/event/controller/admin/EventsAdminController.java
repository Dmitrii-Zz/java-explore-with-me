package ru.practicum.explore.event.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.UpdateEventAdminRequest;
import ru.practicum.explore.event.model.StateEvent;
import ru.practicum.explore.event.service.EventService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
public class EventsAdminController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Object> searchEvents(@RequestParam(defaultValue = "") List<Long> users,
                                               @RequestParam(defaultValue = "") List<StateEvent> states,
                                               @RequestParam(defaultValue = "") List<Long> idsCategory,
                                               @RequestParam(required = false) String rangeStart,
                                               @RequestParam(required = false) String rangeEnd,
                                               @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                               @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Запрос на поиск событий.");
        return eventService.searchEvents(users, states, idsCategory, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<Object> updateEvent(
            @PathVariable @Positive int eventId,
            @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        log.info("Обновление события id = {} админом", eventId);
        return eventService.updateEventAdmin(eventId, updateEventAdminRequest);
    }
}
