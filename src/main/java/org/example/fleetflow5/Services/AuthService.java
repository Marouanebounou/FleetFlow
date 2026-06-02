package org.example.fleetflow5.Services;

import org.example.fleetflow5.Dtos.UserDto;

public interface AuthService {
    String register(UserDto userDto);
    String login(UserDto userDto);
}
