package ru.practicum.explore.controller;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.dto.HitDto;
import ru.practicum.explore.service.HitService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class HitController {

    private final HitService hitService;

    @PostMapping("/hit")
    public void createHit(@Validated @RequestBody HitDto hitDto) {
        log.info("Сохранение uri {} (ip = {}).", hitDto.getUri(), hitDto.getIp());
        hitService.createHit(hitDto);
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getHit(@RequestParam @NotNull String start,
                                         @RequestParam @NotNull String end,
                                         @RequestParam(defaultValue = "") List<String> uris,
                                         @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("Запрос статистики.");
        return hitService.getHit(start, end, uris, unique);
    }
}
