package com.example.edufood.service;

import com.example.edufood.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {



    OrderResponseDto createOrderFromCart(String cartCookie, String email);

    OrderResponseDto getOrderForOwner(Long orderId, String email);

    List<OrderResponseDto> getOrderHistory(String email);
}
