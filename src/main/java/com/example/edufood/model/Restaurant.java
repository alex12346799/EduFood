package com.example.edufood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    private String imageUrl;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();
}
