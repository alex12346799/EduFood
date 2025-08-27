package com.example.edufood.service;

import com.example.edufood.dto.UserRequestDto;
import com.example.edufood.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRequestDto dto);
    UserResponseDto login(UserRequestDto userRequestDto);
    UserResponseDto getById(Long id);
    UserResponseDto getByemail(String email);
}
