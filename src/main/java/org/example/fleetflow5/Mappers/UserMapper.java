package org.example.fleetflow5.Mappers;

import org.example.fleetflow5.Dtos.UserRegisterDto;
import org.example.fleetflow5.Models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRegisterDto toDto(User user);
}
