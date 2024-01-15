package ru.practicum.explore.compilation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.compilation.dto.NewCompilationDto;

@Slf4j
@RestController
@RequestMapping("/admin/compilations")
public class CompilationsAdminController {

    @PostMapping
    public ResponseEntity<Object> addCompilation(@RequestBody NewCompilationDto newCompilationDto) {
        return null;
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<Object> deleteCompilation(@PathVariable long compId) {
        return null;
    }

    @PatchMapping("/{compId}")
    public ResponseEntity<Object> updateCompilation(@PathVariable long compId) {
        return null;
    }
}
