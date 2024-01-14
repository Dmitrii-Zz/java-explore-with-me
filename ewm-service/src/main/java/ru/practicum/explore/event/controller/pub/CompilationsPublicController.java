package ru.practicum.explore.event.controller.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/compilations")
public class CompilationsPublicController {

    @GetMapping
    public ResponseEntity<Object> getCompilations(@RequestParam boolean pinned,
                                                  @RequestParam(defaultValue = "0") int from,
                                                  @RequestParam(defaultValue = "10") int size) {
        log.info("Запрос подборки событий, pinned = {}, from = {}, size = {}", pinned, from, size);
        return null;
    }

    @GetMapping("/{compId}")
    public ResponseEntity<Object> getCompilationById(@PathVariable int compId) {
        log.info("Запрос подборки по id = {}", compId);
        return null;
    }
}
