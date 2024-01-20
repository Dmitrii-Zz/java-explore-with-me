package ru.practicum.explore.request.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.request.service.RequestService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class RequestPrivateController {
    private final RequestService requestService;

    @GetMapping
    public ResponseEntity<Object> getRequests(@PathVariable @Positive long userId) {
        log.info("Запрос заявок юзера id = {}", userId);
        return requestService.getRequests(userId);
    }

    @PostMapping
    public ResponseEntity<Object> createRequest(@PathVariable @Positive long userId,
                                                @RequestParam @Positive long eventId) {
        log.info("Запрос на создание заявки от юзера id = {} на событие id = {}", userId, eventId);
        return requestService.createRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ResponseEntity<Object> cancelRequest(@PathVariable @Positive long userId,
                                                @PathVariable @Positive long requestId) {
        log.info("Отмена заявки id = {} юзером id = {}", requestId, userId);
        return requestService.cancelRequest(userId, requestId);
    }
}