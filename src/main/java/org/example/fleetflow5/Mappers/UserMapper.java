package org.example.fleetflow5.Mappers;

import org.example.fleetflow5.Dtos.UserDto;
import org.example.fleetflow5.Models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
