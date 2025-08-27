package com.example.edufood.mapper;

import com.example.edufood.dto.DishRequestDto;
import com.example.edufood.dto.DishResponseDto;
import com.example.edufood.model.Dish;
import com.example.edufood.model.Restaurant;

public class DishMapper {
    public static Dish fromDto(DishRequestDto dto, Restaurant restaurant) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setPrice(dto.getPrice());
        dish.setRestaurant(restaurant);
        return dish;
    }
    public static DishResponseDto toDto(Dish dish) {
        DishResponseDto dto = new DishResponseDto();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setPrice(dish.getPrice());
        dto.setRestaurantId(dish.getRestaurant() != null ? dish.getRestaurant().getId() : null);
        return dto;
    }

}
