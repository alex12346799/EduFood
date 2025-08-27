package com.example.edufood.mapper;

import com.example.edufood.dto.RestaurantRequestDto;
import com.example.edufood.dto.RestaurantResponseDto;
import com.example.edufood.model.Restaurant;

public class RestaurantMapper {
    public static Restaurant toEntity(RestaurantRequestDto dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        return restaurant;
    }

    public static RestaurantResponseDto toDto(Restaurant entity) {
        RestaurantResponseDto dto = new RestaurantResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}
