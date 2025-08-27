package com.example.edufood.service.impl;

import com.example.edufood.dto.UserRequestDto;
import com.example.edufood.dto.UserResponseDto;
import com.example.edufood.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public UserResponseDto register(UserRequestDto dto) {
//        if (dto.getName() == null || dto.getName().isEmpty()) {
//            throw new NotFoundException("Имя обязательно");
//        }
//        if (dto.getSurname() == null || dto.getSurname().isEmpty()) {
//            throw new NotFoundException("фамилия обязательно");
//        }
//        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
//            throw new NotFoundException("Email  обязательно");
//        }
//        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
//            throw new NotFoundException("Пароль обязательно");
//        }
//        if (userRepository.existsByName(dto.getName())) {
//            throw new NotFoundException("Пользователь с таким именем уже существует");
//        }
        return null;
    }

    @Override
    public UserResponseDto login(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto getById(Long id) {
        return null;
    }

    @Override
    public UserResponseDto getByemail(String email) {
        return null;
    }
}
