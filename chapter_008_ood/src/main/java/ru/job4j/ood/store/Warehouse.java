package ru.job4j.ood.store;

import ru.job4j.ood.store.cycle.DistributionStrategy;

/** Represents warehouse. */
public class Warehouse extends AbstractStore {
    private final DistributionStrategy warehouseStrategy;

    /** Initializes the warehouse with the specified distribution strategy. */
    public Warehouse(DistributionStrategy warehouseStrategy) {
        super("Warehouse");
        this.warehouseStrategy = warehouseStrategy;
    }

    /**
     * Checks if the warehouse can accept the specified food
     * @param food checked food
     * @return true if the store can accept the food instance, false otherwise
     */
    @Override
    public boolean accepts(Food food) {
        return warehouseStrategy.accepts(food);
    }
}
