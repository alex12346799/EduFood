package com.example.edufood.mapper;

import com.example.edufood.dto.RestaurantRequestDto;
import com.example.edufood.dto.RestaurantResponseDto;
import com.example.edufood.model.Restaurant;

public class RestaurantMapper {
    public static Restaurant fromDto(RestaurantRequestDto dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        return restaurant;
    }

    public static RestaurantResponseDto toDto(Restaurant restaurant) {
        RestaurantResponseDto dto = new RestaurantResponseDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        dto.setImageUrl(restaurant.getImageUrl());
        return dto;
    }
}
