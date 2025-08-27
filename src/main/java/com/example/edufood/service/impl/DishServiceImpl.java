package com.example.edufood.service.impl;

import com.example.edufood.exceptions.NotFoundException;
import com.example.edufood.model.Dish;
import com.example.edufood.repository.DishRepository;
import com.example.edufood.repository.RestaurantRepository;
import com.example.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    @Override
    public Dish getById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Блюдо не найдено"));
    }

    @Override
    public List<Dish> getDishesByRestaurant(Long restaurantId) {
        return dishRepository.findByRestaurantId(restaurantId);
    }
}
