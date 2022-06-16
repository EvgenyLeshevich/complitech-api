package com.evgeniy.complitechapi.dto;

import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String login;
    String fullName;
}
