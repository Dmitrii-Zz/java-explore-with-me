package ru.practicum.explore.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.request.model.Request;
import ru.practicum.explore.request.model.RequestStatus;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findByRequesterIdAndEventIdAndStatusIsNotIn(long userId, long eventId, List<RequestStatus> requests);

    Request findByIdAndStatus(long requestId, RequestStatus status);

    List<Request> findAllByRequesterIdAndEventId(long userId, long eventId);

    long countByEventIdAndStatusIsNotIn(long eventId, List<RequestStatus> requests);

    List<Request> findAllByRequesterId(long userId);
}
