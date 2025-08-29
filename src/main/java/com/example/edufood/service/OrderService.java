package com.example.edufood.service;

import com.example.edufood.dto.OrderResponseDto;

import java.util.List;
import java.util.Map;

public interface OrderService {





//    OrderResponseDto createOrderFromCart(Map<Long, Integer> cart, String email);



    List<OrderResponseDto> createOrdersFromCart(Map<Long, Integer> cart, String email);

    OrderResponseDto getOrderForOwner(Long orderId, String email);

    List<OrderResponseDto> getOrderHistory(String email);
}
