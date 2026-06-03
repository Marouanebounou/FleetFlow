package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.UserRegisterDto;
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
    public UserRegisterDto saveUser(UserRegisterDto userRegisterDto) {
        Client client = new Client();
        client.setName(userRegisterDto.getName());
        client.setEmail(userRegisterDto.getEmail());
        client.setPassword(userRegisterDto.getPassword());
        client.setRole(userRegisterDto.getRole());
        Client saved = clientRepository.save(client);
        return userMapper.toDto(saved);
    }
}
