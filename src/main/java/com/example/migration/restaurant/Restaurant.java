package com.example.migration.restaurant;

import com.example.migration.dish.Dish;
import com.example.migration.dish.DishType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Konverter denne vha ctrl + shift + alt + k, rydd deretter opp

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<Dish> dishes;


    private Long dishOfTheDayId;

    public Restaurant() {

    }

    public Restaurant(String name, List<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    public Restaurant(long id, String name, List<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public boolean hasDishType(DishType dishType) {
        return getDishes().stream().anyMatch(dish -> dish.getType().equals(dishType));
    }

    public Long getDishOfTheDayId() {
        return dishOfTheDayId;
    }

    public void setDishOfTheDayId(Long dishOfTheDayId) {
        this.dishOfTheDayId = dishOfTheDayId;
    }

    public Optional<Dish> getDishOfTheDay() {
        if (dishOfTheDayId == null) return Optional.empty();
        return getDishes().stream().filter(dish -> dish.getId().equals(dishOfTheDayId)).findFirst();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(dishes, that.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishes);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
