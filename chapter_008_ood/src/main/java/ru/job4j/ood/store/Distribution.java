package ru.job4j.ood.store;

import ru.job4j.ood.store.cycle.DistributionStrategy;
import ru.job4j.ood.store.cycle.StoreCycle;
import ru.job4j.ood.store.cycle.StoreCycleCalculator;

/**
 * Combines different types of store strategies under a single product life cycle
 * adopted for a specific storage group.
 */
public class Distribution {
    private final StoreCycleCalculator cycleCalculator;

    /** Initializes the instance with the specified cycle calculator. */
    public Distribution(StoreCycleCalculator cycleCalculator) {
        this.cycleCalculator = cycleCalculator;
    }

    /** Creates distribution strategy for the specified store cycle state. */
    public DistributionStrategy createFor(StoreCycle state) {
        return item -> cycleCalculator.calculate(item) == state;
    }

    public DistributionStrategy warehouseStrategy() {
        return createFor(StoreCycle.STORAGE);
    }

    public DistributionStrategy shopStrategy() {
        return createFor(StoreCycle.FOR_SALE);
    }

    public DistributionStrategy discountStrategy() {
        return createFor(StoreCycle.DISCOUNT_SALE);
    }

    public DistributionStrategy trashStrategy() {
        return createFor(StoreCycle.EXPIRED);
    }
}
