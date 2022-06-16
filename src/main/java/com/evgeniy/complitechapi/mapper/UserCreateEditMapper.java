package com.evgeniy.complitechapi.mapper;

import com.evgeniy.complitechapi.database.entity.User;
import com.evgeniy.complitechapi.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto dto, User user) {
        copy(dto, user);
        return user;
    }

    @Override
    public User map(UserCreateEditDto dto) {
        User user = new User();
        copy(dto, user);

        return user;
    }

    private void copy(UserCreateEditDto dto, User user) {
        user.setLogin(dto.getLogin());
        user.setFullName(dto.getFullName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
}
