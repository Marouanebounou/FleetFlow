package org.example.fleetflow5.Services;

import org.example.fleetflow5.Dtos.UserLoginDto;
import org.example.fleetflow5.Dtos.UserRegisterDto;

public interface AuthService {
    String register(UserRegisterDto userRegisterDto);
    String login(UserLoginDto userLoginDto);
}
