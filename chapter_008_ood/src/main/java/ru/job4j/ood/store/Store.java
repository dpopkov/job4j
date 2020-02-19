package ru.job4j.ood.store;

import java.util.List;

/**
 * Represents interface for a food store that can accept or reject food items.
 */
public interface Store {
    /** Adds the specified food to the store.
     * @param food added food instance
     */
    void add(Food food);

    /** Checks if the store can accept the specified food
     * @param food checked food
     * @return true if the store can accept the food instance, false otherwise
     */
    boolean accepts(Food food);

    /**
     * Takes and removes all foods from the store
     * @return list of taken food
     */
    List<Food> takeAll();
}
