package com.evgeniy.complitechapi.mapper;

import com.evgeniy.complitechapi.database.entity.User;
import com.evgeniy.complitechapi.dto.UserReadDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User user) {
        return new UserReadDto(
                user.getId(),
                user.getLogin(),
                user.getFullName()
        );
    }
}
