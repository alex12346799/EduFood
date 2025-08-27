package com.example.edufood.service.impl;

import com.example.edufood.exceptions.NotFoundException;
import com.example.edufood.model.Restaurant;
import com.example.edufood.repository.RestaurantRepository;
import com.example.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    @Override
    public Restaurant getById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Заведение не найдено"));
    }
}
