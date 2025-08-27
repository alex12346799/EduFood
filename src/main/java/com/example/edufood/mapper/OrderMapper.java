package com.example.edufood.mapper;

import com.example.edufood.dto.OrderItemResponseDto;
import com.example.edufood.dto.OrderRequestDto;
import com.example.edufood.dto.OrderResponseDto;
import com.example.edufood.model.Order;
import com.example.edufood.model.OrderItem;
import com.example.edufood.model.Restaurant;
import com.example.edufood.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        dto.setRestaurantId(order.getRestaurant() != null ? order.getRestaurant().getId() : null);
        BigDecimal total = order.getItems().stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalPrice(total);
        List<OrderItemResponseDto> itemDto = order.getItems().stream()
                .map(OrderItemMapper::toDto)
                .collect(Collectors.toList());
        dto.setItems(itemDto);
        return dto;


    }


}
