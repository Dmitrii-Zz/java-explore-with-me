package ru.practicum.explore.event.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.UpdateEventAdminRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/events")
public class EventsAdminController {
    @GetMapping
    public ResponseEntity<Object> searchEvents(@RequestParam List<Long> users,
                                               @RequestParam List<String> states,
                                               @RequestParam List<Long> ids,
                                               @RequestParam String rangeStart,
                                               @RequestParam String rangeEnd,
                                               @RequestParam(defaultValue = "0") int from,
                                               @RequestParam(defaultValue = "10") int size) {
        return null;
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<Object> updateEvent(
            @PathVariable int eventId,
            @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }
}
