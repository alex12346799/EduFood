//package com.example.edufood.mapper;
//
//import com.example.edufood.dto.OrderItemResponseDto;
//import com.example.edufood.dto.OrderRequestDto;
//import com.example.edufood.dto.OrderResponseDto;
//import com.example.edufood.model.Order;
//import com.example.edufood.model.OrderItem;
//import com.example.edufood.model.Restaurant;
//import com.example.edufood.model.User;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class OrderMapper {
//
//    public static OrderResponseDto toDto(Order order) {
//        OrderResponseDto dto = new OrderResponseDto();
//        dto.setId(order.getId());
//        dto.setCreateTime(order.getCreateTime());
//        dto.setStatus(order.getStatus());
//        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
//        dto.setRestaurantId(order.getRestaurant() != null ? order.getRestaurant().getId() : null);
//        BigDecimal total = order.getItems().stream()
//                .map(OrderItem::getPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        dto.setTotalPrice(total);
//        List<OrderItemResponseDto> itemDto = order.getItems().stream()
//                .map(OrderItemMapper::toDto)
//                .collect(Collectors.toList());
//        dto.setItems(itemDto);
//        return dto;
//
//
//    }
//
//
//}
package com.example.edufood.mapper;

import com.example.edufood.dto.OrderItemResponseDto;
import com.example.edufood.dto.OrderResponseDto;
import com.example.edufood.model.Order;
import com.example.edufood.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {

    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setUserId(order.getUser().getId());

        if (order.getRestaurant() != null) {
            dto.setRestaurantId(order.getRestaurant().getId());
            dto.setRestaurantName(order.getRestaurant().getName());
        }

        dto.setCreateTime(order.getCreateTime());

        BigDecimal totalPrice = order.getItems().stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalPrice(totalPrice);

        List<OrderItemResponseDto> items = order.getItems().stream().map(item -> {
            OrderItemResponseDto itemDto = new OrderItemResponseDto();
            itemDto.setId(item.getId());
            itemDto.setOrderId(order.getId());
            itemDto.setDishId(item.getDish().getId());
            itemDto.setDishName(item.getDish().getName());
            itemDto.setQuantity(item.getQuantity());
            itemDto.setPrice(item.getPrice());
            return itemDto;
        }).toList();
        dto.setItems(items);

        return dto;
    }
}
