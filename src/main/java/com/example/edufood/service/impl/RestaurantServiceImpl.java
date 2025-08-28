package com.example.edufood.service.impl;

import com.example.edufood.exceptions.NotFoundException;
import com.example.edufood.model.Restaurant;
import com.example.edufood.repository.RestaurantRepository;
import com.example.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant getById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Заведение не найдено"));
    }

//    @Override
//    public List<Restaurant> getRestaurants(String query, Pageable pageable) {
//        if (query != null && !query.isEmpty()) {
//            return restaurantRepository.findByNameContainingIgnoreCase(query, pageable);
//        }
//        return restaurantRepository.findAll(pageable);
//    }
@Override
public Page<Restaurant> getRestaurants(String query, Pageable pageable) {
    if (query != null && !query.isEmpty()) {
        // Поиск по имени с пагинацией
        return restaurantRepository.findByNameContainingIgnoreCase(query, pageable);
    }
    // Без поиска — просто все рестораны с пагинацией
    return restaurantRepository.findAll(pageable);
}

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

}
