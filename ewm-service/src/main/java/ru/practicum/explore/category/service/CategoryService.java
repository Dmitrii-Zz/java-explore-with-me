package ru.practicum.explore.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.mapper.CategoryMapper;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.repository.CategoryRepository;
import ru.practicum.explore.except.ex.CategoryNotFountException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<Object> createCategory(CategoryDto categoryDto) {
        Category saveCategory = categoryRepository.save(CategoryMapper.toCategory(categoryDto));
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteCategory(int catId) {

        if (categoryRepository.findById(catId).isEmpty()) {
            throw new CategoryNotFountException("Категория не найдена или недоступна");
        }

        categoryRepository.deleteById(catId);

        return new ResponseEntity<>("Категория удалена", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateCategory(int catId, CategoryDto categoryDto) {
        Category category = CategoryMapper.toCategory(categoryDto);
        category.setId(catId);
        Category saveCategory = categoryRepository.save(category);
        return new ResponseEntity<>(saveCategory, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}