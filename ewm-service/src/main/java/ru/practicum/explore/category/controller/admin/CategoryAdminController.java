package ru.practicum.explore.category.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.service.CategoryService;

import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody @Validated CategoryDto categoryDto) {
        log.info("Запрос на создание категории name = {}", categoryDto.getName());
        return categoryService.createCategory(categoryDto);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable @Positive int catId) {
        log.info("Запрос на удаление категории id = {}", catId);
        return categoryService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    public ResponseEntity<Object> updateCategory(@PathVariable @Positive int catId,
                                                 @RequestBody CategoryDto categoryDto) {
        log.info("Запрос на обновление категории id = {}, name = {}", catId, categoryDto.getName());
        return categoryService.updateCategory(catId, categoryDto);
    }
}
