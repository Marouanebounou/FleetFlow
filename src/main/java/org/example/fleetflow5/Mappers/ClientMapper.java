package org.example.fleetflow5.Mappers;

import org.example.fleetflow5.Dtos.ClientDto;
import org.example.fleetflow5.Models.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
