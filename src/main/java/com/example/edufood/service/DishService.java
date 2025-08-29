package com.example.edufood.service;

import com.example.edufood.model.Dish;
import com.example.edufood.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DishService {
    Dish getById(Long id);

    Page<Dish> getDishesByRestaurant(Long restaurantId, Pageable pageable);
}
