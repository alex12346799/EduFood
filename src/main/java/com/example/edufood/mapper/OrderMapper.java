package com.example.edufood.mapper;

import com.example.edufood.dto.OrderRequestDto;
import com.example.edufood.dto.OrderResponseDto;
import com.example.edufood.model.Order;
import com.example.edufood.model.Restaurant;
import com.example.edufood.model.User;

public class OrderMapper {
    public static Order toEntity(OrderRequestDto dto, User user, Restaurant restaurant) {
        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setStatus("NEW");
        return order;
    }
    public static OrderResponseDto toDto(Order entity) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setRestaurantId(entity.getRestaurant() != null ? entity.getRestaurant().getId() : null);
        return dto;
    }
}
