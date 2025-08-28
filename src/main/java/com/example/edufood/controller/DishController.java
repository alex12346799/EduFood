package com.example.edufood.controller;

import com.example.edufood.model.Dish;
import com.example.edufood.model.Restaurant;
import com.example.edufood.service.DishService;
import com.example.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class DishController {
    private final RestaurantService restaurantService;
    private final DishService dishService;
    @GetMapping("/restaurant/{id}/dishes")
    public String listDishes(@PathVariable("id") Long  restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        List<Dish> dishes = dishService.getDishesByRestaurant(restaurant.getId());
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("dishes", dishes);
        return "dishes/dishes";
    }
}
