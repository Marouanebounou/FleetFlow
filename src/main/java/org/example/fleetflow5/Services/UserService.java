package org.example.fleetflow5.Services;

import org.example.fleetflow5.Dtos.UserRegisterDto;

public interface UserService {
    UserRegisterDto saveUser(UserRegisterDto userRegisterDto);
}
