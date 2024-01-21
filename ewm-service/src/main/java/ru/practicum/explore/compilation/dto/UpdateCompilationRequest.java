package ru.practicum.explore.compilation.dto;

import com.sun.istack.Nullable;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCompilationRequest {

    private List<Long> events;

    private boolean pinned;

    @NotNull
    @Size(min = 1, max = 50)
    private String title;
}
