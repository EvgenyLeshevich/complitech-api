package com.evgeniy.complitechapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageDto {
    private String user;
    private String action;
}
