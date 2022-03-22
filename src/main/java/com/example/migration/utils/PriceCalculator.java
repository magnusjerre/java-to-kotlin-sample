package com.example.migration.utils;

import com.example.migration.dish.Dish;

import java.math.BigInteger;
import java.time.DayOfWeek;

public class PriceCalculator {

    public static int calculatePrice(Dish dish, DayOfWeek dayOfWeek) {
        switch (dish.getType()) {
            case KEBAB:
            case FALAFFEL: return (dish.getPrice().add(BigInteger.valueOf(2 * dayOfWeek.ordinal()))).intValue();
            case BURRITO: return dish.getPrice().intValue();
            default: throw new RuntimeException("Unsupported enum");
        }
    }
}
