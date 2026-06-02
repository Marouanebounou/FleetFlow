package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.UserDto;
import org.example.fleetflow5.Enums.Role;
import org.example.fleetflow5.Models.User;
import org.example.fleetflow5.Repositories.UserRepository;
import org.example.fleetflow5.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public String register(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null){
            throw  new IllegalArgumentException("Email already exists");
        }
        if (userDto.getRole() == null){
            userDto.setRole(Role.ROLE_CLIENT);
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.saveUser(userDto);
        return jwtService.generateToken(userDto.getEmail());
    }

    @Override
    public String login(UserDto userDto){
        if (userRepository.existsUserByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Invalid Credentials");
        }
        User user = userRepository.findByEmail(userDto.getEmail());
        if (!passwordEncoder.matches(userDto.getPassword() , user.getPassword())){
            throw new IllegalArgumentException("Invalid Credentials");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userDto.getEmail(), userDto.getPassword()
                )
        );

        return jwtService.generateToken(user.getEmail());
    }
}
