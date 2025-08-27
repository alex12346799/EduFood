package com.example.edufood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private String status;
    private Long userId;
    private Long restaurantId;
}
