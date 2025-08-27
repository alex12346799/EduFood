package com.example.edufood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DishRequestDto {
    private String name;
    private BigDecimal price;
    private Long restaurantId;
}
