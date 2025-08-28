package com.example.edufood.service;

import com.example.edufood.dto.UserRequestDto;
import com.example.edufood.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponseDto register(UserRequestDto dto);
    UserResponseDto login(String email, String password);
    UserResponseDto getById(Long id);
    UserResponseDto getByEmail(String email);
}
