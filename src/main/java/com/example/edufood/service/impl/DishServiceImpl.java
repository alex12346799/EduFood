package com.example.edufood.service.impl;

import com.example.edufood.exceptions.NotFoundException;
import com.example.edufood.model.Dish;
import com.example.edufood.repository.DishRepository;
import com.example.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Dish> getDishesByRestaurant(Long restaurantId, Pageable pageable) {
        return dishRepository.findByRestaurantId(restaurantId, pageable);
    }
}
