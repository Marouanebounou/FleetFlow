package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.UserLoginDto;
import org.example.fleetflow5.Dtos.UserRegisterDto;
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
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public String register(UserRegisterDto userRegisterDto) {
        if (userRepository.findByEmail(userRegisterDto.getEmail()) != null){
            throw  new IllegalArgumentException("Email already exists");
        }
        if (userRegisterDto.getRole() == null){
            userRegisterDto.setRole(Role.ROLE_CLIENT);
        }
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userService.saveUser(userRegisterDto);
        return jwtService.generateToken(userRegisterDto.getEmail());
    }

    @Override
    public String login(UserLoginDto userLoginDto){
        if (!userRepository.existsUserByEmail(userLoginDto.getEmail())){
            throw new IllegalArgumentException("Invalid Credentials");
        }
        User user = userRepository.findByEmail(userLoginDto.getEmail());
        if (!passwordEncoder.matches(userLoginDto.getPassword() , user.getPassword())){
            throw new IllegalArgumentException("Invalid Credentials");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(), userLoginDto.getPassword()
                )
        );

        return jwtService.generateToken(user.getEmail());
    }
}
