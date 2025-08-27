package com.example.edufood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
}
