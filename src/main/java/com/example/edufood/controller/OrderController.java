package com.example.edufood.controller;

import com.example.edufood.dto.OrderRequestDto;
import com.example.edufood.dto.OrderResponseDto;
import com.example.edufood.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/history")
    public String getOrderHistory(Model model, Authentication authentication) {
        String email = authentication.getName();
        List<OrderResponseDto> orders = orderService.getOrderHistory(email);
        model.addAttribute("orders", orders);
        return "order/history";
    }

    @GetMapping("/{id}")
    public String getOrderDetail(Model model, Authentication authentication, @PathVariable("id") Long orderId) {
        String email = authentication.getName();
        OrderResponseDto order = orderService.getOrderForOwner(orderId, email);
        model.addAttribute("order", order);
        return "order/detail";
    }

    @PostMapping("/create")
    public String createOrder(@CookieValue("cart") String cartCookie,
                              HttpServletResponse response, Authentication authentication) {
        if (cartCookie == null || cartCookie.isEmpty()) {
            return "redirect:/login";
        }
        String email = authentication.getName();
        orderService.createOrderFromCart(cartCookie, email);
        Cookie cookie = new Cookie("cart", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/orders/history";
    }
}
