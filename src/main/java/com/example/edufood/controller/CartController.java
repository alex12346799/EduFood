package com.example.edufood.controller;

import com.example.edufood.model.Dish;
import com.example.edufood.service.DishService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final DishService dishService;
    private final ObjectMapper mapper;

    @PostMapping("/add/{dishId}")
    public String addToCart (@PathVariable Long dishId, @CookieValue(defaultValue = "{}") String cartCookie,
                             @RequestParam (defaultValue = "1") int quantity, HttpServletResponse response) throws JsonProcessingException {
        Map<Long, Integer> cart;
        try{
            cart = mapper.readValue(cartCookie, new TypeReference<>() {});
        }catch (Exception e){
            cart = new HashMap<>();
        }
        cart.put(dishId, cart.getOrDefault(dishId, 0) + quantity);
        Cookie cookie = new Cookie("cart", mapper.writeValueAsString(cart));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        return "redirect:/restaurant/" + dishService.getById(dishId).getRestaurant().getId()+"/dishes/dishes";
    }


//    @GetMapping
//    public String viewCart(@CookieValue(value = "cart", defaultValue = "{}") String cartCookie,
//                           Model model) {
//        Map<Long, Integer> cart;
//        try {
//            cart = mapper.readValue(cartCookie, new TypeReference<>() {});
//        } catch (Exception e) {
//            cart = new HashMap<>();
//        }
//
//        Map<Dish, Integer> dishesInCart = new HashMap<>();
//        cart.forEach((dishId, quantity) -> {
//            Dish dish = dishService.getById(dishId);
//            dishesInCart.put(dish, quantity);
//        });
//
//        model.addAttribute("dishesInCart", dishesInCart);
//        return "cart/cart";
//    }

    @GetMapping
    public String viewCart(@CookieValue(value = "cart", defaultValue = "{}") String cartCookie,
                           Model model) {
        Map<Long, Integer> cart;
        try {
            cart = mapper.readValue(cartCookie, new TypeReference<>() {});
        } catch (Exception e) {
            cart = new HashMap<>();
        }

        Map<Dish, Integer> dishesInCart = new HashMap<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Dish dish = dishService.getById(entry.getKey());
            int quantity = entry.getValue();
            dishesInCart.put(dish, quantity);
            totalPrice = totalPrice.add(dish.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }

        model.addAttribute("dishesInCart", dishesInCart);
        model.addAttribute("totalPrice", totalPrice);
        return "cart/cart";
    }


    @PostMapping("/remove/{dishId}")
    public String removeFromCart(@PathVariable Long dishId,
                                 @CookieValue(value = "cart", defaultValue = "{}") String cartCookie,
                                 HttpServletResponse response) throws JsonProcessingException {
        Map<Long, Integer> cart;
        try {
            cart = mapper.readValue(cartCookie, new TypeReference<>() {});
        } catch (Exception e) {
            cart = new HashMap<>();
        }

        cart.remove(dishId);

        Cookie cookie = new Cookie("cart", mapper.writeValueAsString(cart));
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);

        return "redirect:/cart";
    }
}
