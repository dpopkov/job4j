package ru.job4j.ood.store.cycle;

/**
 * Strategy used for distribution and sorting expirable items.
 */
public interface DistributionStrategy {
    /** Returns true the the item is accepted, false otherwise. */
    boolean accepts(Expirable item);
}
