package ru.job4j.ood.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Abstract food collection.
 */
public abstract class FoodCollection implements Consumer<Food> {
    /** List of collected food entities. */
    private final List<Food> storedFood = new ArrayList<>();
    /** Name of the food collection. */
    private final String name;

    /** Initializes food collection with the specified name. */
    public FoodCollection(String name) {
        this.name = name;
    }

    /** Accepts the specified food entity. */
    @Override
    public void accept(Food food) {
        storedFood.add(food);
    }

    /** Returns true if contains the specified food entity. */
    public boolean contains(Food food) {
        return storedFood.contains(food);
    }

    @Override
    public String toString() {
        return name;
    }
}
