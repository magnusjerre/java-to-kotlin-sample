package com.example.migration.dish;

import com.example.migration.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DishType type;
    private String name;
    private BigInteger price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;


    public Dish() {

    }

    public Dish(DishType type, String name) {
        this(null, type, name, BigInteger.ZERO);
    }

    public Dish(Long id, DishType type, String name) {
        this(id, type, name, BigInteger.ZERO);
    }

    public Dish(Long id, DishType type, String name, BigInteger price) {
        this.id = id;
        this.type = type;
        this.name = name;
        // Bruker setPrice for at validering skal slå til
        setPrice(price);
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        if (price == null || price.compareTo(BigInteger.valueOf(140)) > -1) {
            throw new IllegalArgumentException("Price too high, must be less than 140, but was " + price);
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && type == dish.type && name.equals(dish.name) && price.equals(dish.price) && Objects.equals(restaurant, dish.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, price, restaurant);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }

    public Dish copy() {
        return new Dish(id, type, name, price);
    }
}
