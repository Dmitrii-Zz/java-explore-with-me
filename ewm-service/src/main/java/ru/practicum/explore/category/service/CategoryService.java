package ru.practicum.explore.category.service;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.mapper.CategoryMapper;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.repository.CategoryRepository;
import ru.practicum.explore.except.ex.CategoryNotFountException;
import ru.practicum.explore.utils.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryStorage;

    public ResponseEntity<Object> createCategory(CategoryDto categoryDto) {
        Category saveCategory = categoryStorage.save(CategoryMapper.toCategory(categoryDto));
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteCategory(int catId) {

        if (categoryStorage.findById(catId).isEmpty()) {
            throw new CategoryNotFountException("Категория не найдена или недоступна");
        }

        categoryStorage.deleteById(catId);

        return new ResponseEntity<>("Категория удалена", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> updateCategory(int catId, CategoryDto categoryDto) {
        Category category = CategoryMapper.toCategory(categoryDto);
        category.setId(catId);
        Category saveCategory = categoryStorage.save(category);
        return new ResponseEntity<>(saveCategory, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAll(int from, int size) {
        PageRequest page = Page.createPageRequest(from, size);
        List<CategoryDto> categories = categoryStorage.getAllCategory(page).stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public ResponseEntity<Object> getCategoryById(int catId) {
        Optional<Category> optionalCategory = categoryStorage.findById(catId);

        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFountException("Категория не найдена или недоступна");
        }

        CategoryDto categoryDto = CategoryMapper.toCategoryDto(optionalCategory.get());

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
}