package com.example.migration.dish;

import com.example.migration.utils.PriceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("dishes")
public class DishRestController {

    @Autowired
    private DishRepository dishRepository;

    private Logger logger = LoggerFactory.getLogger(DishRestController.class);

    @GetMapping
    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Dish> getDish(@PathVariable Long id) {
        return dishRepository.findById(id);
    }

    @GetMapping("{id}/price")
    public Map<String, Integer> price(@PathVariable Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (!dish.isPresent()) {
            throw new EntityNotFoundException("Couldn't find dish with id " + id);
        }

        Map<String, Integer> prices = new HashMap<>();
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            prices.put(dayOfWeek.name(), PriceCalculator.calculatePrice(dish.get(), dayOfWeek));
        }
        return prices;
    }

    @PostMapping
    public Dish addDish(@RequestBody Dish dish) {
        var newDish = dishRepository.save(dish);
        logger.info("POST for new dish received and saved to db:\n"
                + "{\n"
                + "\t\"id\":" + newDish.getId() + "\n"
                + "\t\"name\":\"" + newDish.getName() + "\"\n"
                + "\t\"price\":" + newDish.getPrice() + "\n"
                + "}");
        return newDish;
    }
}
