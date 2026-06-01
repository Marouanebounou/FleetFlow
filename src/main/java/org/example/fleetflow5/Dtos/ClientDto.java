package org.example.fleetflow5.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto extends UserDto{
    @NotBlank(message = "Ville is Obligatory")
    private String ville;
}
