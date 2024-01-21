package ru.practicum.explore.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.compilation.controller.mapper.CompilationMapper;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;
import ru.practicum.explore.compilation.dto.UpdateCompilationRequest;
import ru.practicum.explore.compilation.model.Compilation;
import ru.practicum.explore.compilation.repository.CompilationRepository;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.service.EventService;
import ru.practicum.explore.except.ex.CompilationNotFountException;
import ru.practicum.explore.utils.Page;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompilationService {
    private final CompilationRepository compilationStorage;
    private final EventService eventService;

    public ResponseEntity<Object> createCompilation(NewCompilationDto newCompilationDto) {
        Compilation compilation = CompilationMapper.toCompilation(newCompilationDto);
        Set<Event> events = new HashSet<>();

        for (long eventId : newCompilationDto.getEvents()) {
            events.add(eventService.checkExistsEvent(eventId));
        }

        compilation.setEvents(events);
        CompilationDto compilationDto = CompilationMapper.toCompilationDto(compilationStorage.save(compilation));
        return new ResponseEntity<>(compilationDto, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteCompilation(long compId) {
        checkExistsCompilation(compId);
        return new ResponseEntity<>("Подборка удалена.", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> updateCompilation(long compId, UpdateCompilationRequest updateCompilationRequest) {
        Compilation compilation = checkExistsCompilation(compId);
        return null;
    }

    public ResponseEntity<Object> getCompilations(Boolean pinned, int from, int size) {
        PageRequest page = Page.createPageRequest(from, size);
        return null;
    }

    public ResponseEntity<Object> getCompilationById(long compId) {
        Compilation compilation = checkExistsCompilation(compId);
        return null;
    }

    private Compilation checkExistsCompilation(long compId) {
        Optional<Compilation> compilationOptional = compilationStorage.findById(compId);

        if (compilationOptional.isEmpty()) {
            throw new CompilationNotFountException("Compilation with " + compId + " was not found");
        }

        return compilationOptional.get();
    }
}
