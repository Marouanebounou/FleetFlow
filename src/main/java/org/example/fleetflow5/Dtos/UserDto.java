package org.example.fleetflow5.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fleetflow5.Enums.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    @NotBlank(message = "Name is Obligatory")
    private String name;
    @NotBlank(message = "Email is Obligatory")
    @Email(message = "Email format is incorrect")
    private String email;
    @NotBlank(message = "Password is obligatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private Role role;
}
