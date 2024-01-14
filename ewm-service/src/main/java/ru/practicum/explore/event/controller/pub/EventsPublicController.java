package ru.practicum.explore.event.controller.pub;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
public class EventsPublicController {

    @GetMapping
    public ResponseEntity<Object> getEvents(@RequestParam String text,
                                            @RequestParam List<Long> categories,
                                            @RequestParam boolean paid,
                                            @RequestParam String rangeStart,
                                            @RequestParam String rangeEnd,
                                            @RequestParam(defaultValue = "false") boolean onlyAvailable,
                                            @RequestParam String sort,
                                            @RequestParam(defaultValue = "0") int from,
                                            @RequestParam(defaultValue = "10") int size) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable int id) {
        return null;
    }
}
