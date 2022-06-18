package com.evgeniy.complitechapi.http.rest.client;

import com.evgeniy.complitechapi.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Communication {

    @Value("#{new String('http://localhost:'+'${server.port}'+'/api/auth/registration')}")
    private String URL;

    private final RestTemplate restTemplate;

    public String createUser(UserCreateEditDto user) {
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(URL, user, String.class);
        return responseEntity.getBody();
    }
}