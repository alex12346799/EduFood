package com.example.edufood.mapper;

import com.example.edufood.dto.UserRequestDto;
import com.example.edufood.dto.UserResponseDto;
import com.example.edufood.model.User;

public class UserMapper {
    public static User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDto toDto(User entity) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
