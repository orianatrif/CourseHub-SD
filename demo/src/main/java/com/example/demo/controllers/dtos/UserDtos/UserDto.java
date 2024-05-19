package com.example.demo.controllers.dtos.UserDtos;

import com.example.demo.entities.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @Pattern(regexp = "(?i)^[A-Z]+$", message = "Invalid firstname format")
    private String firstname;
    @Pattern(regexp = "(?i)^[A-Z]+$", message = "Invalid lastname format")
    private String lastname;
    @NotNull(message = "User role is empty")
    private UserRole userRole;
    @Pattern(regexp = "(?i)^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Invalid email format")
    private String email;
    private String password;
    private String token;
}
