package com.example.edufood.controller;

import com.example.edufood.model.Restaurant;
import com.example.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @GetMapping("/restaurants")
    public String restaurants(@RequestParam(required = false) String query, Model model) {
        List<Restaurant> restaurants = restaurantService.getRestaurants(query);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("query", query);
        return "restaurants/restaurants";
    }
}
