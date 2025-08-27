package com.example.edufood.service;

import com.example.edufood.model.Dish;
import com.example.edufood.model.Restaurant;

import java.util.List;

public interface DishService {
    Dish getById(Long id);
    List<Dish> getDishesByRestaurant(Long restaurantId);
}
