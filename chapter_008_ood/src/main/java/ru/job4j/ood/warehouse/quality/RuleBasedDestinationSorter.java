package ru.job4j.ood.warehouse.quality;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The sorter uses rules for determining destination of sorted entities according to expiration state.
 */
public class RuleBasedDestinationSorter<T extends Expirable & Discountable> implements DestinationSorter<T> {
    /** Maps state of an entity to its destination. */
    private final Map<StoreCycle, DestId> destinations = new EnumMap<>(StoreCycle.class);
    /** Maps state of an entity to action that should be applied to it. */
    private final Map<StoreCycle, Consumer<T>> actions = new EnumMap<>(StoreCycle.class);
    /** Calculator of entity state. */
    private final StoreCycleCalculator stateCalculator;
    /** Empty action that does nothing. */
    private final Consumer<T> emptyAction = (f) -> { };

    /**
     * Constructs and initializes the sorter with the specified state calculator.
     * @param stateCalculator calculator of entity state
     */
    public RuleBasedDestinationSorter(StoreCycleCalculator stateCalculator) {
        this.stateCalculator = stateCalculator;
    }

    /**
     * Sets rule that is used for determining entity's destination.
     * @param state state of entity
     * @param destinationId destination id
     * @param action action that should be applied to sorted entity
     */
    public void setRule(StoreCycle state, DestId destinationId, Consumer<T> action) {
        destinations.put(state, destinationId);
        actions.put(state, action);
    }

    /**
     * Sets rule that is used for determining entity's destination.
     * @param state state of entity
     * @param destinationId destination id
     */
    public void setRule(StoreCycle state, DestId destinationId) {
        destinations.put(state, destinationId);
    }

    /**
     * Determines destination ID for the specified entity using current rules of the sorter.
     */
    @Override
    public DestId sort(T entity) {
        StoreCycle state = stateCalculator.calculate(entity);
        actions.getOrDefault(state, emptyAction).accept(entity);
        return destinations.get(state);
    }
}
