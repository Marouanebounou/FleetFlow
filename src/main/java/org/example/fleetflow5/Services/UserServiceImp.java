package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.UserDto;
import org.example.fleetflow5.Mappers.UserMapper;
import org.example.fleetflow5.Models.Client;
import org.example.fleetflow5.Repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final ClientRepository clientRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        Client client = new Client();
        client.setName(userDto.getName());
        client.setEmail(userDto.getEmail());
        client.setPassword(userDto.getPassword());
        client.setRole(userDto.getRole());
        Client saved = clientRepository.save(client);
        return userMapper.toDto(saved);
    }
}
