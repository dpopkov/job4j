package ru.job4j.ood.store;

import ru.job4j.ood.store.cycle.DistributionStrategy;

/**
 * Represents trash for food items.
 */
public class Trash extends AbstractStore {
    private final DistributionStrategy trashStrategy;

    /** Constructs and initializes trash with the specified distribution strategy. */
    public Trash(DistributionStrategy trashStrategy) {
        super("Trash");
        this.trashStrategy = trashStrategy;
    }

    /**
     * Checks if the trash can accept the specified food
     * @param food checked food
     * @return true if the trash can accept the food instance, false otherwise
     */
    @Override
    public boolean accepts(Food food) {
        return trashStrategy.accepts(food);
    }
}
