package com.example.edufood.mapper;

import com.example.edufood.dto.OrderItemRequestDto;
import com.example.edufood.dto.OrderItemResponseDto;
import com.example.edufood.model.Dish;
import com.example.edufood.model.Order;
import com.example.edufood.model.OrderItem;

public class OrderItemMapper {
    public static OrderItem fromDto(OrderItemRequestDto dto, Order order, Dish dish) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setDish(dish);
        orderItem.setQuantity(dto.getQuantity());
        return orderItem;
    }

    public static OrderItemResponseDto toDto(OrderItem orderItem) {
        OrderItemResponseDto dto = new OrderItemResponseDto();
        dto.setId(orderItem.getId());
        dto.setOrderId(orderItem.getOrder() != null ? orderItem.getOrder().getId() : null);
        dto.setDishId(orderItem.getDish() != null ? orderItem.getDish().getId() : null);
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }
}
