package com.example.edufood.mapper;

import com.example.edufood.dto.OrderItemRequestDto;
import com.example.edufood.dto.OrderItemResponseDto;
import com.example.edufood.model.Dish;
import com.example.edufood.model.Order;
import com.example.edufood.model.OrderItem;

public class OrderItemMapper {
    public static OrderItem toEntity(OrderItemRequestDto dto, Order order, Dish dish) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setDish(dish);
        orderItem.setQuantity(dto.getQuantity());
        return orderItem;
    }

    public static OrderItemResponseDto toDto(OrderItem entity) {
        OrderItemResponseDto dto = new OrderItemResponseDto();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrder() != null ? entity.getOrder().getId() : null);
        dto.setDishId(entity.getDish() != null ? entity.getDish().getId() : null);
        dto.setQuantity(entity.getQuantity());
        return dto;
    }
}
