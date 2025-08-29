package com.example.edufood.controller;

import com.example.edufood.model.Dish;
import com.example.edufood.service.DishService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("cart")
@Slf4j
public class CartController {
    private final DishService dishService;
    private final ObjectMapper mapper;

    @PostMapping("/add/{dishId}")
    public String addToCart(@PathVariable Long dishId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpServletRequest request) {
        Map<Long, Integer> cart = getCart(request);

        int newQuantity = cart.getOrDefault(dishId, 0) + quantity;
        if (newQuantity > 0) {
            cart.put(dishId, newQuantity);
        } else {
            cart.remove(dishId);
        }

        request.getSession().setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{dishId}")
    public String removeFromCart(@PathVariable Long dishId, HttpServletRequest request) {
        Map<Long, Integer> cart = getCart(request);
        cart.remove(dishId);
        request.getSession().setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(HttpServletRequest request, Model model) {
        Map<Long, Integer> cart = getCart(request);

        Map<Dish, Integer> dishesInCart = new HashMap<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Dish dish = dishService.getById(entry.getKey());
            int quantity = entry.getValue();
            dishesInCart.put(dish, quantity);
            if (dish.getPrice() != null) {
                totalPrice = totalPrice.add(dish.getPrice().multiply(BigDecimal.valueOf(quantity)));
            }
        }

        model.addAttribute("dishesInCart", dishesInCart);
        model.addAttribute("totalPrice", totalPrice);
        return "cart/cart";
    }


    private Map<Long, Integer> getCart(HttpServletRequest request) {
        Object sessionCart = request.getSession().getAttribute("cart");
        Map<Long, Integer> cart = new HashMap<>();
        if (sessionCart instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getKey() instanceof Long key && entry.getValue() instanceof Integer value) {
                    cart.put(key, value);
                }
            }
        }
        return cart;
    }


}
