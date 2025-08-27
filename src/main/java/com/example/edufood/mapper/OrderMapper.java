package com.example.edufood.mapper;

import com.example.edufood.dto.OrderRequestDto;
import com.example.edufood.dto.OrderResponseDto;
import com.example.edufood.model.Order;
import com.example.edufood.model.Restaurant;
import com.example.edufood.model.User;

public class OrderMapper {
    public static Order fromDto(OrderRequestDto dto, User user, Restaurant restaurant) {
        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setStatus("NEW");
        return order;
    }
    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        dto.setRestaurantId(order.getRestaurant() != null ? order.getRestaurant().getId() : null);
        return dto;
    }
}
