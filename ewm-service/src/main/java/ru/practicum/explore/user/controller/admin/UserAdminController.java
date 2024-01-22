package ru.practicum.explore.user.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.user.dto.NewUserRequest;
import ru.practicum.explore.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserAdminController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getUser(@RequestParam(defaultValue = "") List<Long> ids,
                                          @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                          @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Запрос списка пользователей, параметры from = {}, size = {}", from, size);
        return userService.getUsers(ids, from, size);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("Запрос на сохранение пользователя email = {}", newUserRequest.getEmail());
        return userService.createUsers(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable @PositiveOrZero long userId) {
        log.info("Удаление пользователя id = {}", userId);
        return userService.deleteUser(userId);
    }
}