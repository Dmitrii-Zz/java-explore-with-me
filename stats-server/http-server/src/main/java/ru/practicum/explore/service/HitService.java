package ru.practicum.explore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.dto.HitDto;
import ru.practicum.explore.exeptions.ex.LocalDataTimeParseException;
import ru.practicum.explore.exeptions.ex.ValidatedDataTimeException;
import ru.practicum.explore.mapper.HitMapper;
import ru.practicum.explore.model.Hit;
import ru.practicum.explore.repository.HitRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HitService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final HitRepository storage;

    public ResponseEntity<Object> createHit(HitDto hitDto) {
        Hit hit = HitMapper.toHit(hitDto);
        hit.setTimestamp(LocalDateTime.parse(hitDto.getTimestamp(), FORMATTER));
        return new ResponseEntity<>(HitMapper.toHitDto(storage.save(hit)), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getHit(String strStart, String strEnd, List<String> uris, Boolean unique) {
        LocalDateTime start = parseDataTime(strStart);
        LocalDateTime end = parseDataTime(strEnd);

        log.info(String.valueOf(uris.size()));

        if (start.equals(end) || start.isAfter(end)) {
            throw new ValidatedDataTimeException("Некорректные даты в запросе.");
        }
        if (uris.isEmpty() && !unique) {
            return new ResponseEntity<>(storage.getStatsAllUrisAndNotUniqueIp(start, end), HttpStatus.OK);
        }

        if (uris.isEmpty() && unique) {
            return new ResponseEntity<>(storage.getStatsAllUrisAndUniqueIp(start, end), HttpStatus.OK);
        }

        if (!unique) {
            return new ResponseEntity<>(storage.getStatsUrisAndNotUniqueIp(start, end, uris), HttpStatus.OK);
        }

        return new ResponseEntity<>(storage.getStatsUrisAndUniqueIp(start, end, uris), HttpStatus.OK);
    }

    private LocalDateTime parseDataTime(String value) {
        try {
            return LocalDateTime.parse(value, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new LocalDataTimeParseException("Дата должна быть в формате yyyy-MM-dd HH:mm:ss");
        }
    }
}
