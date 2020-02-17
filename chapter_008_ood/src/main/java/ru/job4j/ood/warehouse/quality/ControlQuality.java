package ru.job4j.ood.warehouse.quality;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Distributes entities into destinations using an external sorting strategy
 * represented by {@link DestinationSorter} instance.
 */
public class ControlQuality<T extends Expirable & Discountable> {
    private static final Logger LOG = LogManager.getLogger(ControlQuality.class);

    /** Maps destination id to real destinations. */
    private final Map<DestId, Consumer<T>> destinations = new EnumMap<>(DestId.class);
    /** Strategy used for determining destination of sorted entities. */
    private DestinationSorter<T> sortingStrategy;

    /** Initializes instance with the specified sorting strategy.
     * @param sortingStrategy strategy used for determining destination of sorted entities
     */
    public ControlQuality(DestinationSorter<T> sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    /** Sets sorting strategy.
     * @param sortingStrategy strategy used for determining destination of sorted entities
     */
    public void setSortingStrategy(DestinationSorter<T> sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    /**
     * Adds destination for sorted entities.
     * @param id destination id
     * @param destination destination that should receive sorted entities
     */
    public void addDestination(DestId id, Consumer<T> destination) {
        destinations.put(id, destination);
    }

    /**
     * Distributes the specified entities to destinations using current sorting strategy algorithm.
     * @param entityList list of entities to distribute
     */
    public void sort(List<T> entityList) {
        for (T entity : entityList) {
            DestId id = sortingStrategy.sort(entity);
            Consumer<T> consumer = destinations.get(id);
            if (consumer != null) {
                LOG.trace("{} goes to {}", entity, consumer);
                consumer.accept(entity);
            }
        }
    }
}
