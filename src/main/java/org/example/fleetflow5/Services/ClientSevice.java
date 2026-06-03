package org.example.fleetflow5.Services;

import org.example.fleetflow5.Dtos.ClientDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientSevice {
    ClientDto save(ClientDto client);
    void delete(long id);
    ClientDto update(long id, ClientDto client);
    List<ClientDto> getAllClient(Pageable pageable);
}
