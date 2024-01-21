package ru.practicum.explore.compilation.model;

import lombok.*;
import ru.practicum.explore.event.model.Event;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compilations")
public class Compilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Set<Event> events;

    @Column(nullable = false)
    private boolean pinned;

    @Column(nullable = false)
    private String title;
}
