package ru.practicum.explore.category.mapper;

import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.model.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }

    public static CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
