package ru.practicum.explore.event.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.StateEvent;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiatorId(long userId, PageRequest page);

    List<Event> findAllByInitiatorIdInOrStateInOrCategoryIdIn(List<Long> users,
                                                                List<StateEvent> states,
                                                                List<Long> idsCategory);
}
