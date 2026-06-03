package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.ClientDto;
import org.example.fleetflow5.Mappers.ClientMapper;
import org.example.fleetflow5.Models.Client;
import org.example.fleetflow5.Repositories.ClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientSevice{

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDto save(ClientDto client) {
        Client saved = clientRepository.save(clientMapper.toEntity(client));
        return clientMapper.toDto(saved);
    }

    public void delete(long id) {
        clientRepository.deleteById(id);
    }

    public List<ClientDto> getAllClient(Pageable pageable) {
        return clientRepository.findAll(pageable).stream().map(clientMapper::toDto).toList();
    }

    public ClientDto update(long id, ClientDto client) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        existing.setName(client.getName());
        existing.setEmail(client.getEmail());
        existing.setPhone(client.getPhone());
        existing.setVille(client.getVille());

        Client updated = clientRepository.save(existing);
        return clientMapper.toDto(updated);
    }
}
