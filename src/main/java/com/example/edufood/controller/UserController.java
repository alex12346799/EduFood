package com.example.edufood.controller;

import com.example.edufood.dto.UserRequestDto;
import com.example.edufood.dto.UserResponseDto;
import com.example.edufood.service.OrderService;
import com.example.edufood.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final View error;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new  UserRequestDto());
        return "users/register";
    }
    @PostMapping("/register")
    public String register(@Valid  UserRequestDto userRequestDto , BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("user", userRequestDto);
            return "users/register";
        }
        userService.register(userRequestDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(defaultValue = "false") boolean error) {
        model.addAttribute("error", error);
        return "users/login";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        UserResponseDto user = userService.getByEmail(authentication.getName());
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/profile/orders")
    public String orderHistory(Model model, Authentication authentication) {
        String email = authentication.getName();
        UserResponseDto user = userService.getByEmail(email);
        model.addAttribute("orders", orderService.getOrderHistory(email));
        return "users/orderHistory";
    }


}
