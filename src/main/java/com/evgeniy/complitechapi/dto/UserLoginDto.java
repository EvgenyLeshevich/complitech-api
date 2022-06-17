package com.evgeniy.complitechapi.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class UserLoginDto {
    @NotBlank
    String login;

    @NotBlank
    String password;
}
