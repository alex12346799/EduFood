package com.example.edufood.service.impl;

import com.example.edufood.dto.OrderResponseDto;
import com.example.edufood.exceptions.NotFoundException;
import com.example.edufood.mapper.OrderMapper;
import com.example.edufood.model.*;
import com.example.edufood.repository.DishRepository;
import com.example.edufood.repository.OrderRepository;
import com.example.edufood.repository.RestaurantRepository;
import com.example.edufood.repository.UserRepository;
import com.example.edufood.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<OrderResponseDto> createOrdersFromCart(Map<Long, Integer> cart, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));


        Map<Restaurant, List<Map.Entry<Dish, Integer>>> dishesByRestaurant = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Dish dish = dishRepository.findById(entry.getKey())
                    .orElseThrow(() -> new NotFoundException("Блюдо с ID " + entry.getKey() + " не найден"));
            dishesByRestaurant
                    .computeIfAbsent(dish.getRestaurant(), r -> new ArrayList<>())
                    .add(Map.entry(dish, entry.getValue()));
        }

        List<OrderResponseDto> ordersDto = new ArrayList<>();


        for (Map.Entry<Restaurant, List<Map.Entry<Dish, Integer>>> entry : dishesByRestaurant.entrySet()) {
            Restaurant restaurant = entry.getKey();
            List<Map.Entry<Dish, Integer>> dishes = entry.getValue();

            Order order = new Order();
            order.setUser(user);
            order.setRestaurant(restaurant);
            order.setStatus("NEW");
            order.setCreateTime(LocalDateTime.now());

            List<OrderItem> items = new ArrayList<>();

            for (Map.Entry<Dish, Integer> dishEntry : dishes) {
                Dish dish = dishEntry.getKey();
                Integer quantity = dishEntry.getValue();

                OrderItem item = new OrderItem();
                item.setOrder(order);
                item.setDish(dish);
                item.setQuantity(quantity);
                item.setPrice(dish.getPrice().multiply(BigDecimal.valueOf(quantity)));

                items.add(item);
            }

            order.setItems(items);
            orderRepository.save(order);

            ordersDto.add(OrderMapper.toDto(order));
        }

        return ordersDto;
    }



    @Override
    public OrderResponseDto getOrderForOwner(Long orderId, String email) {
        Order order =  orderRepository.findById(orderId)
                .orElseThrow(()->new NotFoundException("Заказ не найден"));
        if (!order.getUser().getEmail().equals(email)) {
            throw new NotFoundException("Доступ запрещен");
        }
        return OrderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrderHistory(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        return orderRepository.findByUser(user).stream()
                .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                .map(OrderMapper::toDto)
                .toList();
    }



}