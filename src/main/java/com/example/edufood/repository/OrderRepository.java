package com.example.edufood.repository;

import com.example.edufood.model.Order;
import com.example.edufood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
