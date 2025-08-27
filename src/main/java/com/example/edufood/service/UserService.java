package com.example.edufood.service;

import com.example.edufood.dto.UserRequestDto;
import com.example.edufood.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRequestDto dto);
    UserResponseDto login(String email, String password);
    UserResponseDto getById(Long id);
    UserResponseDto getByEmail(String email);
}
