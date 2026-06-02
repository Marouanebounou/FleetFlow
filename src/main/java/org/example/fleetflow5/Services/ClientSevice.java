package org.example.fleetflow5.Services;

import org.example.fleetflow5.Dtos.ClientDto;

public interface ClientSevice {
    ClientDto save(ClientDto client);
    void delete(long id);
    ClientDto update(long id, ClientDto client);
}
