package com.example.edufood.controller;

import com.example.edufood.model.Restaurant;
import com.example.edufood.repository.RestaurantRepository;
import com.example.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
public String restaurants(@RequestParam(required = false) String query, Model model,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

    Page<Restaurant> page = restaurantService.getRestaurants(query, pageable);
    page.getContent().forEach(r -> System.out.println(r.getImageUrl()));
    model.addAttribute("page", page);
    model.addAttribute("restaurants", page.getContent());
    model.addAttribute("url", "/restaurants");
    model.addAttribute("query", query);

    return "restaurants/restaurants";
}
}
