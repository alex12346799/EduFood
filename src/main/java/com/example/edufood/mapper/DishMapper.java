package com.example.edufood.mapper;

import com.example.edufood.dto.DishRequestDto;
import com.example.edufood.dto.DishResponseDto;
import com.example.edufood.model.Dish;
import com.example.edufood.model.Restaurant;

public class DishMapper {
    public static Dish toEntity(DishRequestDto dto, Restaurant restaurant) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setPrice(dto.getPrice());
        dish.setRestaurant(restaurant);
        return dish;
    }
    public static DishResponseDto toDto(Dish entity) {
        DishResponseDto dto = new DishResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setRestaurantId(entity.getRestaurant() != null ? entity.getRestaurant().getId() : null);
        return dto;
    }

}
