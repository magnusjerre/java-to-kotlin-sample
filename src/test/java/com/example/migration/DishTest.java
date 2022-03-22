package com.example.migration;

import com.example.migration.dish.Dish;
import com.example.migration.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DishTest {

    @Test
    @DisplayName("Copy should work")
    public void copyShouldWork() {
        var kebabIPita = new Dish(DishType.KEBAB, "Kebab i pita");
        var kebabIRull = kebabIPita.copy();
        kebabIRull.setName("Kebab i rull");

        Assertions.assertNotEquals(kebabIPita, kebabIRull);
        Assertions.assertEquals(kebabIPita.getType(), kebabIRull.getType());
    }

}
