package com.example.edufood.service;

import com.example.edufood.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant getById(Long id);

    List<Restaurant> getRestaurants(String query);

    List<Restaurant> findAll();
}
