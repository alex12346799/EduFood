package com.example.edufood.service.impl;

import com.example.edufood.dto.OrderResponseDto;
import com.example.edufood.exceptions.NotFoundException;
import com.example.edufood.mapper.OrderMapper;
import com.example.edufood.model.Dish;
import com.example.edufood.model.Order;
import com.example.edufood.model.OrderItem;
import com.example.edufood.model.User;
import com.example.edufood.repository.DishRepository;
import com.example.edufood.repository.OrderRepository;
import com.example.edufood.repository.RestaurantRepository;
import com.example.edufood.repository.UserRepository;
import com.example.edufood.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public OrderResponseDto createOrderFromCart(String cartCookie, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Order order = new Order();
        order.setUser(user);
        order.setStatus("NEW");
        order.setCreateTime(LocalDateTime.now());
        Map<Long,Integer> cart;
        try{
            ObjectMapper mapper = new ObjectMapper();
            cart = mapper.readValue(cartCookie, new TypeReference<>() {});
        }catch (Exception e){
            throw new IllegalArgumentException("Некорректная корзина");
        }
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();
        for (Map.Entry<Long,Integer> entry : cart.entrySet()) {
            Long dishId = entry.getKey();
            Integer quantity = entry.getValue();
            Dish dish = dishRepository.findById(dishId)
                    .orElseThrow(() -> new NotFoundException("Блюдо с ID " + dishId + " не найден"));
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setDish(dish);
            item.setQuantity(quantity);
            item.setPrice(dish.getPrice().multiply(BigDecimal.valueOf(quantity)));
            items.add(item);
            totalPrice = totalPrice.add(item.getPrice());
        }
        order.setItems(items);
        orderRepository.save(order);
        return OrderMapper.toDto(order);
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