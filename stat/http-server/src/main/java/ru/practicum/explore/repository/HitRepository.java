package ru.practicum.explore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explore.model.Hit;

import java.time.LocalDateTime;
import java.util.List;
import ru.practicum.explore.dto.StatsDto;

public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query("select new ru.practicum.explore.dto.StatsDto(h.app, h.uri, COUNT (h.ip)) " +
           "from Hit as h " +
           "where h.timestamp >= ?1 " +
           "and h.timestamp <= ?2 " +
           "group by h.app, h.uri " +
           "order by count (h.ip)")
    List<StatsDto> getStatsAllUrisAndNotUniqueIp(LocalDateTime start, LocalDateTime end);

    @Query("select new ru.practicum.explore.dto.StatsDto(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "from Hit as h " +
            "where h.timestamp >= ?1 " +
            "and h.timestamp <= ?2 " +
            "group by h.app, h.uri " +
            "order by count (h.ip)")
    List<StatsDto> getStatsAllUrisAndUniqueIp(LocalDateTime start, LocalDateTime end);

    @Query("select new ru.practicum.explore.dto.StatsDto(h.app, h.uri, COUNT (h.ip)) " +
            "from Hit as h " +
            "where h.timestamp >= ?1 " +
            "and h.timestamp <= ?2 " +
            "and h.uri in ?3 " +
            "group by h.app, h.uri " +
            "order by count (h.ip)")
    List<StatsDto> getStatsUrisAndNotUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("select new ru.practicum.explore.dto.StatsDto(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "from Hit as h " +
            "where h.timestamp >= ?1 " +
            "and h.timestamp <= ?2 " +
            "and h.uri in ?3 " +
            "group by h.app, h.uri " +
            "order by count (h.ip)")
    List<StatsDto> getStatsUrisAndUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);


}
