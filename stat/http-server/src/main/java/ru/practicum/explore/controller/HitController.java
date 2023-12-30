package ru.practicum.explore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.explore.model.Hit;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class HitController {


    @PostMapping("/hit")
    public ResponseEntity<Object> createHit(@RequestBody Hit hit) {
        log.info("Выполнен запрос (ip = {}) на сохранение сервиса {}", hit.getIp(), hit.getApp());
        return null;
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getHit() {
        return null;
    }
}
