package ru.practicum.explore.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explore.model.Hit;

@Service
public class HitService {

    public ResponseEntity<Object> createHit(Hit hit) {

        return new ResponseEntity<>("Информация сохранена", HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getHit(int id) {
        return null;
    }

}
