package ru.job4j.ood.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Base store class that collects food items.
 */
public abstract class AbstractStore implements Store {
    /** Name of the store. */
    private final String name;
    /** Stored food items. */
    private List<Food> foods = new ArrayList<>();

    /** Creates and initializes the store with the specified name. */
    public AbstractStore(String name) {
        this.name = name;
    }

    /**
     * Adds the specified food to the store.
     * @param food added food instance
     */
    @Override
    public void add(Food food) {
        foods.add(food);
    }

    /**
     * Takes and removes all foods from the store.
     * @return list of taken food
     */
    @Override
    public List<Food> takeAll() {
        List<Food> taken = foods;
        foods = new ArrayList<>();
        return taken;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Checks if the store contains the specified food instance.
     * @param food food to check
     * @return true if the store contains the food, false otherwise
     */
    @Override
    public boolean contains(Food food) {
        return foods.contains(food);
    }

    /**
     * Checks if the store is empty.
     * @return true if the store is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return foods.isEmpty();
    }
}
