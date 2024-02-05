package ru.practicum.explore.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.except.ex.UserNotFountException;
import ru.practicum.explore.user.dto.NewUserRequest;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.mapper.UserMapper;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.repository.UserRepository;
import ru.practicum.explore.utils.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userStorage;

    public ResponseEntity<Object> getUsers(List<Long> ids, int from, int size) {
        PageRequest page = Page.createPageRequest(from, size);
        List<UserDto> users;

        if (ids.isEmpty()) {
            users = userStorage.findAll(page).stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
        } else {
            users = userStorage.findByIdIn(ids, page).stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<Object> createUsers(NewUserRequest newUserRequest) {
        User user = UserMapper.toUser(newUserRequest);
        User saveUser = userStorage.save(user);
        return new ResponseEntity<>(UserMapper.toUserDto(saveUser), HttpStatus.CREATED);
    }

    public void deleteUser(long id) {
        checkExistsUser(id);
        userStorage.deleteById(id);
    }

    public User checkExistsUser(long id) {
        Optional<User> optionalUser = userStorage.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFountException("Пользователь не найден или недоступен");
        }

        return optionalUser.get();
    }
}
