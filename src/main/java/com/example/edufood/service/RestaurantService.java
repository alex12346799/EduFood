package com.example.edufood.service;

import com.example.edufood.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {
    Restaurant getById(Long id);

//    List<Restaurant> getRestaurants(String query);

    //    @Override
    //    public List<Restaurant> getRestaurants(String query, Pageable pageable) {
    //        if (query != null && !query.isEmpty()) {
    //            return restaurantRepository.findByNameContainingIgnoreCase(query, pageable);
    //        }
    //        return restaurantRepository.findAll(pageable);
    //    }
    Page<Restaurant> getRestaurants(String query, Pageable pageable);

    List<Restaurant> findAll();
}
