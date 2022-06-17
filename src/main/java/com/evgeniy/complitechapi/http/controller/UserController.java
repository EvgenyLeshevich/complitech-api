package com.evgeniy.complitechapi.http.controller;

import com.evgeniy.complitechapi.dto.UserCreateEditDto;
import com.evgeniy.complitechapi.dto.UserReadDto;
import com.evgeniy.complitechapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all users")
    @GetMapping
    public List<UserReadDto> findAll() {
        return userService.findAll();
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Validated @RequestBody UserCreateEditDto user) {
        return userService.create(user);
    }

    @Operation(summary = "Update user")
    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id,
                              @Validated @RequestBody UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
