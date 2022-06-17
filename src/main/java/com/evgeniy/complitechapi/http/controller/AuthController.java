package com.evgeniy.complitechapi.http.controller;

import com.evgeniy.complitechapi.config.jwt.JWTUtil;
import com.evgeniy.complitechapi.dto.UserCreateEditDto;
import com.evgeniy.complitechapi.dto.UserLoginDto;
import com.evgeniy.complitechapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> registration(@Validated @RequestBody UserCreateEditDto user) {
        userService.create(user);
        String token = jwtUtil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginDto user) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(user.getLogin(),
                        user.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);
    }
}
