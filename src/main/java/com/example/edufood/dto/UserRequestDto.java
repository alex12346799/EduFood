package com.example.edufood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Long roleId;
}
