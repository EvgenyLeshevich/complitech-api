package com.evgeniy.complitechapi.service;

import com.evgeniy.complitechapi.database.repository.UserRepository;
import com.evgeniy.complitechapi.dto.MessageDto;
import com.evgeniy.complitechapi.dto.UserCreateEditDto;
import com.evgeniy.complitechapi.dto.UserReadDto;
import com.evgeniy.complitechapi.mapper.UserCreateEditMapper;
import com.evgeniy.complitechapi.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final SimpMessagingTemplate template;

    public List<UserReadDto> findAll(HttpServletRequest request) {
        String URI = request.getRequestURI();
        String methodName = request.getMethod();
        String activeUserName = request.getUserPrincipal().getName();
        MessageDto message = MessageDto.builder()
                .user(activeUserName)
                .action("use request " + methodName + " " + URI)
                .build();
        template.convertAndSend("/topic/messages", message);

        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean deleteUsersWithIdBetween(Long from, Long to) {
        if (userRepository.selectUsersWithIdBetween(from, to).size() == 0) {
            return false;
        } else {
            userRepository.deleteUsersWithIdBetween(from, to);
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        new HashSet<GrantedAuthority>()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + login));
    }
}
