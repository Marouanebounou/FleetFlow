package org.example.fleetflow5.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.UserLoginDto;
import org.example.fleetflow5.Dtos.UserRegisterDto;
import org.example.fleetflow5.Services.AuthServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImp authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto userRegisterDto){
        String token = authService.register(userRegisterDto);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDto userLoginDto){
        String token = authService.login(userLoginDto);
        return ResponseEntity.ok(token);
    }

}
