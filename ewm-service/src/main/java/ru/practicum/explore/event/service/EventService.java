package ru.practicum.explore.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.service.CategoryService;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.mapper.EventMapper;
import ru.practicum.explore.event.mapper.LocationMapper;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.Location;
import ru.practicum.explore.event.model.StateEvent;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.service.UserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventStorage;
    private final UserService userService;
    private final CategoryService categoryService;
    private final LocationService locationService;

    public ResponseEntity<Object> getEvent(int userId, int from, int size) {
        return null;
    }

    public ResponseEntity<Object> createEvent(long userId, NewEventDto newEventDto) {
        User user = userService.checkExistsUser(userId);
        Category category = categoryService.checkExistCategory(newEventDto.getCategory());
        Location location = locationService.createLocation(LocationMapper.toLocation(newEventDto.getLocation()));

        Event event = EventMapper.toEvent(newEventDto);
        event.setInitiator(user);
        event.setCategory(category);
        event.setLocation(location);
        event.setCreatedOn(LocalDateTime.now());
        event.setState(StateEvent.PENDING);

        EventFullDto eventFullDto = EventMapper.toEventFullDto(eventStorage.save(event));
        return new ResponseEntity<>(eventFullDto, HttpStatus.CREATED);
    }
}
