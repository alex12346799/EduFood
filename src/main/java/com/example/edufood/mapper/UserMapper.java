    package com.example.edufood.mapper;

    import com.example.edufood.dto.UserRequestDto;
    import com.example.edufood.dto.UserResponseDto;
    import com.example.edufood.model.User;

    public class UserMapper {
        public static User fromDto(UserRequestDto dto) {
            User user = new User();
            user.setName(dto.getName());
            user.setSurname(dto.getSurname());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            return user;
        }

        public static UserResponseDto toDto(User user) {
            UserResponseDto dto = new UserResponseDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setEmail(user.getEmail());
            return dto;
        }
    }
