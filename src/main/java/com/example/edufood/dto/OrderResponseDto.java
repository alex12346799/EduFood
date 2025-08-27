package com.example.edufood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private String status;
    private Long userId;
    private Long restaurantId;
    private BigDecimal totalPrice;
    private List<OrderItemResponseDto> items;
}
