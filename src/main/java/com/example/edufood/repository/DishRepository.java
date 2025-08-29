package com.example.edufood.repository;

import com.example.edufood.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DishRepository extends JpaRepository<Dish, Long> {
    Page<Dish> findByRestaurantId(Long restaurantId, Pageable pageable);
}
