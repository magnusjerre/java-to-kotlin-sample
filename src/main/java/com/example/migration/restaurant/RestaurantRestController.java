package com.example.migration.restaurant;

import com.example.migration.dish.Dish;
import com.example.migration.dish.DishType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("restaurants")
public class RestaurantRestController {

    @Autowired
    RestaurantRepository repository;

    @GetMapping
    public List<Restaurant> getRestaurants(@RequestParam(required = false) String dishType) {

        if (dishType != null) {
            DishType type = DishType.valueOf(dishType.toUpperCase());
            return repository.findAll().stream().filter(restaurant -> restaurant.hasDishType(type)).collect(Collectors.toList());
        }


        return repository.findAll();
    }

    @GetMapping("dishes")
    public List<Dish> getAllDishes() {
        return repository.findAll().stream().flatMap(restaurant -> restaurant.getDishes().stream()).collect(Collectors.toList());
    }

    @GetMapping("{id}/dishOfTheDay")
    public Optional<Dish> getDishOfTheDay(@PathVariable Long id) {
        if (id == null) throw new IllegalArgumentException("Id must not be null");

        return repository.findById(id).flatMap(Restaurant::getDishOfTheDay);

    }

}
