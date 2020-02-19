package ru.job4j.ood.store.cycle;

/**
 * This calculator should be used for determining state of an expirable entity.
 */
public interface StoreCycleCalculator {
    /**
     * Determines state of an expirable entity according to its expiration state.
     * @param expirable expirable entity
     * @return state of entity in the accepted storage cycle
     */
    StoreCycle calculate(Expirable expirable);
}
