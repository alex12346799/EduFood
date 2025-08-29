package com.example.edufood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private String status;
    private Long userId;
    private Long restaurantId;
    private BigDecimal totalPrice;
    private String restaurantName;
    private LocalDateTime createTime;
    private List<OrderItemResponseDto> items;
}
