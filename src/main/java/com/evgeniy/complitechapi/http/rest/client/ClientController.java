package com.evgeniy.complitechapi.http.rest.client;

import com.evgeniy.complitechapi.dto.UserCreateEditDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/client/users")
@RequiredArgsConstructor
public class ClientController {

    private final Communication communication;

    @Operation(summary = "Create user from client")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> saveUserFromClient(@RequestBody UserCreateEditDto user){
        String token = communication.createUser(user);
        return Map.of("jwt-token", token);
    }
}
