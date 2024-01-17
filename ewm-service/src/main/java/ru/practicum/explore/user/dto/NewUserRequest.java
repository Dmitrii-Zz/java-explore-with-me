package ru.practicum.explore.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {

    @Email
    @NotNull
    @Size(min = 6, max = 254, message = "Длина эл.почты должна быть в диапазоне 6-254.")
    private String email;

    @NotNull
    @Size(min = 2, max = 250, message = "Длина эл.почты должна быть в диапазоне 2-250.")
    private String name;
}
