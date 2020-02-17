package ru.job4j.ood.warehouse.quality;

/**
 * The sorter is used for determining destination of sorted entities according to expiration state.
 */
public interface DestinationSorter<T extends Expirable & Discountable> {
    /**
     * Determines destination ID for the specified entity.
     */
    DestId sort(T entity);
}
