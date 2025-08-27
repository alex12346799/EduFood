package com.example.edufood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDto {
    private Long id;
    private Long orderId;
    private Long dishId;
    private int quantity;
}
