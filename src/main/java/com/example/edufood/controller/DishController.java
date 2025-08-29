package com.example.edufood.controller;

import com.example.edufood.model.Dish;
import com.example.edufood.model.Restaurant;
import com.example.edufood.service.DishService;
import com.example.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String listDishes(@PathVariable("id") Long  restaurantId, Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        Page<Dish> page = dishService.getDishesByRestaurant(restaurant.getId(), pageable);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("page", page);
        model.addAttribute("dishes", page.getContent());
        model.addAttribute("url", "/restaurant/" + restaurantId + "/dishes");
        return "dishes/dishes";
    }
}
