package com.example.edufood.controller;

import com.example.edufood.dto.OrderRequestDto;
import com.example.edufood.dto.OrderResponseDto;
import com.example.edufood.exceptions.NotFoundException;
import com.example.edufood.model.User;
import com.example.edufood.repository.UserRepository;
import com.example.edufood.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;

    @GetMapping("/history")
    public String getOrderHistory(Model model, Authentication authentication) {
        String email = authentication.getName();
        List<OrderResponseDto> orders = orderService.getOrderHistory(email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user not found"));
        model.addAttribute("user", user);
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
    public String createOrder(HttpServletRequest request, Authentication authentication) {
        Object sessionCart = request.getSession().getAttribute("cart");
        if (sessionCart == null) {
            return "redirect:/cart";
        }
        Map<Long, Integer> cart = new HashMap<>();
        if (sessionCart instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getKey() instanceof Long key && entry.getValue() instanceof Integer value) {
                    cart.put(key, value);
                }
            }
        }

        if (cart.isEmpty()) {
            return "redirect:/cart";
        }

        String email = authentication.getName();


        orderService.createOrdersFromCart(cart, email);


        request.getSession().removeAttribute("cart");

        return "redirect:/orders/history";
    }
}
