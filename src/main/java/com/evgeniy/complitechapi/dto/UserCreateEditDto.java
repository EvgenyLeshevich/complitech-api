package com.evgeniy.complitechapi.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class UserCreateEditDto {
    @NotBlank
    String login;

    @NotBlank
    String password;

    @Size(min = 3, max = 64)
    String fullName;
}
