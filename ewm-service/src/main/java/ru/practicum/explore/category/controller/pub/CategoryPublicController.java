package ru.practicum.explore.category.controller.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryPublicController {

    @GetMapping
    public ResponseEntity<Object> getCategories(@RequestParam(defaultValue = "0") int from,
                                                @RequestParam(defaultValue = "10") int size) {
        log.info("Запрос на возврат всех категорий, from = {}, size = {}", from, size);
        return null;
    }

    @GetMapping("/{catId}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int catId) {
        log.info("Запрос на возврат категории id = {}", catId);
        return null;
    }
}
