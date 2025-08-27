package com.example.edufood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DishResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long restaurantId;
}
