package ru.practicum.explore.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.mapper.CategoryMapper;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.service.CategoryService;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventUserRequest;
import ru.practicum.explore.event.mapper.EventMapper;
import ru.practicum.explore.event.mapper.LocationMapper;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.Location;
import ru.practicum.explore.event.model.StateEvent;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.except.ex.EventIncorectException;
import ru.practicum.explore.except.ex.EventNotFountException;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.service.UserService;
import ru.practicum.explore.utils.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventStorage;
    private final UserService userService;
    private final CategoryService categoryService;
    private final LocationService locationService;

    public ResponseEntity<Object> createEvent(long userId, NewEventDto newEventDto) {
        User user = userService.checkExistsUser(userId);
        Category category = categoryService.checkExistCategory(newEventDto.getCategory());
        Location location = locationService.createLocation(LocationMapper.toLocation(newEventDto.getLocation()));

        Event event = EventMapper.toEvent(newEventDto);

        if (!event.getEventDate().isAfter(LocalDateTime.now().plusHours(2))) {
            throw new EventIncorectException("Field: eventDate. Error: должно содержать дату, " +
                    "которая еще не наступила. Value:");
        }

        event.setInitiator(user);
        event.setCategory(category);
        event.setLocation(location);
        event.setCreatedOn(LocalDateTime.now());
        event.setState(StateEvent.PUBLISHED);

        EventFullDto eventFullDto = EventMapper.toEventFullDto(eventStorage.save(event));
        return new ResponseEntity<>(eventFullDto, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getEvents(int userId, int from, int size) {
        userService.checkExistsUser(userId);
        PageRequest page = Page.createPageRequest(from, size);
        List<EventShortDto> events = eventStorage.findAllByInitiatorId(userId, page).stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    public ResponseEntity<Object> getEventById(long userId, long eventId) {
        userService.checkExistsUser(userId);
        Event event = checkExistsEvent(eventId);

        if (event.getInitiator().getId() != userId) {
            throw new EventNotFountException("Event with id= " + eventId + " was not found");
        }

        return new ResponseEntity<>(EventMapper.toEventFullDto(event), HttpStatus.OK);
    }

    public Event checkExistsEvent(long eventId) {
        Optional<Event> optionalEvent = eventStorage.findById(eventId);

        if (optionalEvent.isEmpty()) {
            throw new EventNotFountException("Event with id= " + eventId + " was not found");
        }

        return optionalEvent.get();
    }

    public ResponseEntity<Object> updateEvent(long userId, long eventId,
                                              UpdateEventUserRequest updateEventUserRequest) {
        userService.checkExistsUser(userId);

        Event event = checkExistsEvent(eventId);

        if (event.getInitiator().getId() != userId) {
            throw new EventNotFountException("Event with id= " + eventId + " was not found");
        }

        if (updateEventUserRequest.getAnnotation() != null) {
            event.setAnnotation(updateEventUserRequest.getAnnotation());
        }

        if (updateEventUserRequest.getCategory() != null) {
            Category category = categoryService.checkExistCategory(updateEventUserRequest.getCategory());
            event.setCategory(category);
        }

        if (updateEventUserRequest.getDescription() != null) {
            event.setDescription(updateEventUserRequest.getDescription());
        }

        if (updateEventUserRequest.getEventDate() != null &&
                !event.getEventDate().isAfter(LocalDateTime.now().plusHours(2))) {
            throw new EventIncorectException("Field: eventDate. Error: должно содержать дату, " +
                    "которая еще не наступила. Value:");
        } else {
            event.setEventDate(updateEventUserRequest.getEventDate());
        }

        if (updateEventUserRequest.getLocation() != null) {
            Location location = locationService.createLocation(
                    LocationMapper.toLocation(updateEventUserRequest.getLocation()));
        }

        if (updateEventUserRequest.getPaid() != null) {
            event.setPaid(updateEventUserRequest.getPaid());
        }

        if (updateEventUserRequest.getParticipantLimit() != null) {
            event.setParticipantLimit(updateEventUserRequest.getParticipantLimit());
        }

        if (updateEventUserRequest.getRequestModeration() != null) {
            event.setRequestModeration(updateEventUserRequest.getRequestModeration());
        }

        if (updateEventUserRequest.getTitle() != null) {
            event.setTitle(updateEventUserRequest.getTitle());
        }

        return null;

    }
}
