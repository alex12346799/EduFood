package com.example.edufood.service.impl;

import com.example.edufood.dto.UserRequestDto;
import com.example.edufood.dto.UserResponseDto;
import com.example.edufood.exceptions.NotFoundException;
import com.example.edufood.mapper.UserMapper;
import com.example.edufood.model.Role;
import com.example.edufood.model.User;
import com.example.edufood.repository.RoleRepository;
import com.example.edufood.repository.UserRepository;
import com.example.edufood.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public UserResponseDto register(UserRequestDto dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new NotFoundException("Имя обязательно");
        }
        if (dto.getSurname() == null || dto.getSurname().isEmpty()) {
            throw new NotFoundException("Фамилия обязательно");
        }
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new NotFoundException("Email  обязательно");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new NotFoundException("Пароль обязательно");
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new NotFoundException("Пользователь с таким email уже занят");
        }
        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new NotFoundException("Роль не найдена"));

        User user = UserMapper.fromDto(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        user.setEnabled(true);
        userRepository.save(user);
        log.info("User registered: {}", user.getEmail());
        return UserMapper.toDto(user);


    }

    @Override
    public UserResponseDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }
        log.info("User logged in: {}", user.getEmail());

        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto getById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Пользователь с ID " + id + " не найден"));
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Пользователь с email " + email + " не найден"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()

        );
    }
}
