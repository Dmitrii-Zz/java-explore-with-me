package ru.practicum.explore.event.controller.privat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/requests")
public class RequestPrivateController {

    @GetMapping
    public ResponseEntity<Object> getRequests(@PathVariable long userId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Object> createRequest(@PathVariable long userId,
                                                @RequestParam long eventId) {
        return null;
    }

    @PatchMapping("/{requestId}/cancel")
    public ResponseEntity<Object> cancelRequest(@PathVariable long userId,
                                                @PathVariable long requestId) {
        return null;
    }

}
